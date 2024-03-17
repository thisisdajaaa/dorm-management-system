package org.dms.repositories.impl;

import org.dms.annotations.Component;
import org.dms.models.Person;
import org.dms.repositories.spec.IPersonRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class PersonRepositoryImpl implements IPersonRepository {
    private final Map<Integer, Person> people = new HashMap<>();

    @Override
    public void save(Person person) {
        people.put(person.getId(), person);
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return Optional.ofNullable(people.get(id));
    }

    @Override
    public List<Map.Entry<Integer, Person>> findAll() {
        return people.entrySet().stream().toList();
    }
}
