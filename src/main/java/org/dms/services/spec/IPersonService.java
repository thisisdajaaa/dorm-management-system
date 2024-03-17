package org.dms.services.spec;

import org.dms.constants.Role;
import org.dms.models.Person;

import java.util.List;
import java.util.Map;

public interface IPersonService {
    void addPerson(String name, String email, String contactNumber, Role role);

    Person findById(Integer id);

    List<Map.Entry<Integer, Person>> findAll();
}
