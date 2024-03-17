package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.KeyStatus;
import org.dms.constants.Role;
import org.dms.exceptions.KeyException;
import org.dms.exceptions.KitchenKeyLogException;
import org.dms.models.Key;
import org.dms.models.KitchenKeyLog;
import org.dms.models.Person;
import org.dms.repositories.spec.IKitchenKeyLogRepository;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IKitchenKeyLogService;
import org.dms.services.spec.IPersonService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KitchenKeyLogServiceImpl implements IKitchenKeyLogService {
    @Autowired
    private IKitchenKeyLogRepository kitchenKeyLogRepository;

    @Autowired
    private IKeyService keyService;

    @Autowired
    private IPersonService personService;

    @Override
    public void addKitchenKeyLog(LocalDate borrowedStartDate, Integer keyId, Integer personId) {
        Key key = keyService.findById(keyId);
        Person person = personService.findById(personId);

        if (!keyService.isPrimaryKey(key.getId())) throw new KeyException.PrimaryException();
        if (!person.getRole().equals(Role.STUDENT)) throw new KitchenKeyLogException.NotAllowedException();

        keyService.setKeyStatus(key.getId(), KeyStatus.BORROWED);

        KitchenKeyLog kitchenKeyLog = new KitchenKeyLog(borrowedStartDate, key, person);

        kitchenKeyLogRepository.save(kitchenKeyLog);
    }

    @Override
    public void markKitchenKeyLogAsComplete() {
        Optional<KitchenKeyLog> kitchenKeyLog = kitchenKeyLogRepository
                .findAll()
                .stream()
                .filter(x -> x.getValue().getBorrowedEndDate() == null)
                .map(Map.Entry::getValue)
                .findFirst();

        if (kitchenKeyLog.isEmpty()) throw new KitchenKeyLogException.NotFoundException();

        KitchenKeyLog keyLog = kitchenKeyLog.get();
        keyLog.setBorrowedEndDate(LocalDate.now());
        keyService.setKeyStatus(keyLog.getKey().getId(), KeyStatus.AVAILABLE);
    }

    @Override
    public KitchenKeyLog findById(Integer id) {
        return kitchenKeyLogRepository.findById(id).orElseThrow(KitchenKeyLogException.NotFoundException::new);
    }

    @Override
    public List<Map.Entry<Integer, KitchenKeyLog>> findAll() {
        return kitchenKeyLogRepository.findAll();
    }
}
