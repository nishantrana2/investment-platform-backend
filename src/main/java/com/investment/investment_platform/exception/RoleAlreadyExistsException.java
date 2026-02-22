package com.investment.investment_platform.exception;


public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(String name) {
        super("Role already exists: " + name);
    }
}
