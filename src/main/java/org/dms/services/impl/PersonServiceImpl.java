package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.Role;
import org.dms.exceptions.PersonException;
import org.dms.models.Person;
import org.dms.repositories.spec.IPersonRepository;
import org.dms.services.spec.IPersonService;
import org.dms.utils.StringUtil;

import java.util.List;
import java.util.Map;

@Component
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public void addPerson(String name, String email, String contactNumber, String password, Role role) {
        if (!isValidFields(name, email, password)) throw new PersonException.BadRequestException();
        if (isPersonExistsAlready(name, email)) throw new PersonException.AlreadyExistsException();

        personRepository.save(new Person(name, email, contactNumber, password, role));
    }

    @Override
    public Person findById(Integer id) {
        return personRepository.findById(id).orElseThrow(PersonException.NotFoundException::new);
    }

    @Override
    public List<Map.Entry<Integer, Person>> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonByEmailAndPassword(String email, String password) {
        return findAll()
                .stream()
                .filter(x -> x.getValue().getEmail().equals(email))
                .filter(x -> x.getValue().getPassword().equals(password))
                .map(Map.Entry::getValue).findFirst()
                .orElseThrow(PersonException.NotFoundException::new);
    }

    private boolean isPersonExistsAlready(String name, String email) {
        return findAll()
                .stream()
                .filter(x -> x.getValue().getName().equals(name) || x.getValue().getEmail().equals(email))
                .map(Map.Entry::getValue)
                .findAny()
                .isPresent();
    }

    private boolean isValidFields(String name, String email, String password) {
        return StringUtil.isMinValid(email, 4) && StringUtil.isMinValid(name, 1) &&
                StringUtil.isMinValid(password, 4) &&
                StringUtil.isMaxValid(email, 55) && StringUtil.isMaxValid(name, 55) &&
                StringUtil.isMaxValid(password, 20) &&
                StringUtil.isValidEmail(email);
    }
}
