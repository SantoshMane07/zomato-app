package com.santoshmane.zomato_app.exceptions;

import javax.naming.AuthenticationException;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message){
        super(message);
    }
}
