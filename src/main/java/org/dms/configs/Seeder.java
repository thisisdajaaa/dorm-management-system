package org.dms.configs;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.models.Person;
import org.dms.constants.Role;
import org.dms.services.spec.IKeyService;
import org.dms.services.spec.IPersonService;

@Component
public class Seeder {
    @Autowired
    private IKeyService keyService;

    @Autowired
    private IPersonService personService;

    public void run() {
        seedAdmins();
        seedKeys();
    }

    public void seedAdmins() {
        personService.addPerson("Admin1", "admin1@example.com", "1234567890", Role.ADMIN);
        personService.addPerson("Admin2", "admin2@example.com", "0987654321", Role.ADMIN);
    }

    public void seedKeys() {
        keyService.addKey(true);
        keyService.addKey(false);
    }
}
