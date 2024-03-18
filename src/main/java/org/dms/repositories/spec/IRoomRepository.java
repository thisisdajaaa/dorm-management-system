package org.dms.repositories.spec;

import org.dms.constants.Role;
import org.dms.models.Person;
import org.dms.models.Room;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRoomRepository {
    void save(Room Room);

    Optional<Room> findById(Integer id);

    List<Map.Entry<Integer, Room>> findAll();
}
