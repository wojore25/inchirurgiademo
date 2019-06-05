package com.internet.inchirurgiademo.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ContactDto {


    private String name;

    @NotNull
    @Email( message = "Podanie adresu email jest wymagane!")
    private String email;

    private String subject;

    @NotNull
    @Length(min = 1, message = "Wpisz wiadomość.")
    private String message;

    @AssertTrue(message = "Aby wysłać wiadomość musisz powierdzić zapoznanie się z Polityką Prywatności!")
    private boolean RodoConfirmation;

    public ContactDto() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRodoConfirmation() {
        return RodoConfirmation;
    }

    public void setRodoConfirmation(boolean rodoConfirmation) {
        RodoConfirmation = rodoConfirmation;
    }
}
