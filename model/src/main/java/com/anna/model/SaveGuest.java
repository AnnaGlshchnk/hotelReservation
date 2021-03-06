package com.anna.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveGuest {

    @Size(min = 2, max = 15)
    @NotNull
    private String firstName;
    @Size(min = 2, max = 15)
    @NotNull
    private String surname;

    public SaveGuest(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public SaveGuest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}

