package com.investment.investment_platform.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {

        super("User not found with id: " + id);
    }
}
