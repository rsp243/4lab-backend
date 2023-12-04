package backend.model.validators;

import backend.DTO.UsersDTO;

public class UsersValidator extends Validator {
    public UsersValidator validateName(String username) {
        if (username == null || username.isEmpty()) {
            this.addViolation("name", "user login in not set or empty");
        }
        return this;
    }

    public UsersValidator validatePassword(UsersDTO req) {
        if (req.getPassword() == null || req.getPassword().isEmpty()) {
            this.addViolation("password", "user password in not set or empty");
        }
        return this;
    }
}
