package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.Role;
import org.dms.services.spec.IAuthenticationService;

@Component
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private PersonServiceImpl personService;

    @Override
    public void register(String name, String email, String contactNumber, String password) {
        personService.addPerson(name, email, contactNumber, password, Role.STUDENT);
    }
}
