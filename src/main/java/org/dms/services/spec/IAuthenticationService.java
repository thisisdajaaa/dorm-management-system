package org.dms.services.spec;

import org.dms.constants.Role;
import org.dms.models.Person;

import java.util.Optional;

public interface IAuthenticationService {
    void register(String name, String email, String contactNumber, String password);

    void login(String email, String password);

    void logout();

    Optional<Person> getCurrentLoggedInUser();
}
