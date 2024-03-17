package org.dms.repositories.spec;

import org.dms.models.Person;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPersonRepository {
    void save(Person person);

    Optional<Person> findById(Integer id);

    List<Map.Entry<Integer, Person>> findAll();
}
