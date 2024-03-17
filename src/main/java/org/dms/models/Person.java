package org.dms.models;

import org.dms.annotations.AutoIncrement;
import org.dms.constants.Role;
import org.dms.utils.ModelHelper;

public class Person {
    @AutoIncrement
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private Role role;

    public Person(String name, String email, String contactNumber, Role role) {
        ModelHelper.handleAutoIncrement(this);
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.role = role;
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
}