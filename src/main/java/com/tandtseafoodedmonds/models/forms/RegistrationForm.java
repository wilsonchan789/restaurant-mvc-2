package com.tandtseafoodedmonds.models.forms;

import javax.validation.constraints.NotNull;

/**
 * Created by LaunchCode
 */
public class RegistrationForm extends LoginForm {

    @NotNull(message = "Passwords need to match")
    private String verifyPassword;

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        checkPasswordForRegistration();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPasswordForRegistration();
    }

    private void checkPasswordForRegistration() {
        if (!getPassword().equals(verifyPassword)) {
            verifyPassword = null;
        }
    }
}