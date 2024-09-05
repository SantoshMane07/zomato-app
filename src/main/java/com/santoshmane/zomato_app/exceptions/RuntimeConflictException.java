package com.santoshmane.zomato_app.exceptions;

public class RuntimeConflictException extends RuntimeException{
    public RuntimeConflictException(){}
    public RuntimeConflictException(String message){
        super(message);
    }
}
