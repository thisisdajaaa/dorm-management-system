package org.dms.services.impl;

import org.dms.annotations.Autowired;
import org.dms.annotations.Component;
import org.dms.constants.Role;
import org.dms.exceptions.AuthenticationException;
import org.dms.exceptions.PersonException;
import org.dms.models.Person;
import org.dms.services.spec.IAuthenticationService;
import org.dms.services.spec.IPersonService;
import org.dms.utils.StringUtil;

import java.util.Map;
import java.util.Optional;

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
        if (hasLoggedInUsersAlready()) throw new AuthenticationException.NotAllowedException();
        if (!isValidCredentials(email, password)) throw new PersonException.BadRequestException();

        Person person = personService.getPersonByEmailAndPassword(email, password);
        person.setLoggedIn(true);
    }

    @Override
    public void logout() {
        if (!hasLoggedInUsersAlready()) throw new AuthenticationException.NotAllowedException("No users is logged in!");

        Person person = getCurrentLoggedInUser().orElseThrow(() -> new AuthenticationException.NotAllowedException("No user is logged in!"));
        ;
        person.setLoggedIn(false);
    }

    @Override
    public Optional<Person> getCurrentLoggedInUser() {
        return personService
                .findAll()
                .stream()
                .filter(x -> x.getValue().getLoggedIn())
                .findAny()
                .map(Map.Entry::getValue);
    }

    private boolean isValidCredentials(String email, String password) {
        return StringUtil.isMinValid(email, 4) &&
                StringUtil.isMinValid(password, 4) &&
                StringUtil.isMaxValid(password, 20) &&
                StringUtil.isValidEmail(email);
    }

    private boolean hasLoggedInUsersAlready() {
        return personService.findAll()
                .stream()
                .filter(x -> x.getValue().getLoggedIn())
                .map(Map.Entry::getValue)
                .findAny()
                .isPresent();
    }
}
