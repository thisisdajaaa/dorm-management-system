package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.Role;
import org.dms.exceptions.PersonException;
import org.dms.models.Person;
import org.dms.services.spec.IAuthenticationService;
import org.dms.services.spec.IPersonService;
import org.dms.utils.StringUtil;

@Component
public class AuthenticationServiceImpl implements IAuthenticationService {
    @Autowired
    private IPersonService personService;


    @Override
    public void register(String name, String email, String contactNumber, String password) {
        personService.addPerson(name, email, contactNumber, password, Role.STUDENT);
    }

    @Override
    public void login(String email, String password) {
        if (!isValidCredentials(email, password)) throw new PersonException.BadRequestException();

        Person person = personService.getPersonByEmailAndPassword(email, password);
        person.setLoggedIn(true);
    }

    private boolean isValidCredentials(String email, String password) {
        return StringUtil.isMinValid(email, 4) &&
                StringUtil.isMinValid(password, 4) &&
                StringUtil.isMaxValid(password, 20) &&
                StringUtil.isValidEmail(email);
    }
}
