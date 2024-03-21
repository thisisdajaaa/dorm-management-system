package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.constants.Role;
import org.dms.utils.ModelUtil;

import java.util.Objects;

public class Person {
    @AutoIncrement
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private Role role;
    private String password;
    private Boolean isLoggedIn;

    public Person(String name, String email, String contactNumber, String password, Role role) {
        ModelUtil.handleAutoIncrement(this);
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
        this.password = password;
        this.isLoggedIn = false;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    @Override
    public String toString() {
        return STR."""
                Id: \{id}
                Name: \{name}
                Email: \{email}
                Contact Number: \{contactNumber}
                Role: \{role}
                IS_LOGGED_IN: \{isLoggedIn}
                """;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }
}
