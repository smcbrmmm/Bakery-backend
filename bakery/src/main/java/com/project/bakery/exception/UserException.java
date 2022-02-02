package com.project.bakery.exception;

public class UserException extends BaseException {
    public UserException(String code) {
        super("user." + code);
    }

    public static UserException emailNull() {
        return new UserException("register.email.null");
    }

    // CREATE
    public static UserException createEmailNull() {
        return new UserException("create.email.null");
    }

    public static UserException createEmailDuplicated() {
        return new UserException("create.email.duplicated");
    }

    public static UserException createPasswordNull() {
        return new UserException("create.password.null");
    }

    public static UserException createNameNull() {
        return new UserException("create.name.null");
    }
}
