package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.Role;
import org.dms.exceptions.PersonException;
import org.dms.models.Person;
import org.dms.repositories.spec.IPersonRepository;
import org.dms.services.spec.IPersonService;
import java.util.List;
import java.util.Map;

@Component
public class PersonServiceImpl implements IPersonService {
    @Autowired
    private IPersonRepository personRepository;

    @Override
    public void addPerson(String name, String email, String contactNumber, Role role) {
        personRepository.save(new Person(name, email, contactNumber, role));
    }

    @Override
    public Person findById(Integer id) {
        return personRepository.findById(id).orElseThrow(PersonException.NotFoundException::new);
    }

    @Override
    public List<Map.Entry<Integer, Person>> findAll() {
        return personRepository.findAll();
    }
}
