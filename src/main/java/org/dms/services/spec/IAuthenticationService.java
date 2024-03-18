package org.dms.services.spec;

import org.dms.constants.Role;

public interface IAuthenticationService {
    void register(String name, String email, String contactNumber, String password);
}
