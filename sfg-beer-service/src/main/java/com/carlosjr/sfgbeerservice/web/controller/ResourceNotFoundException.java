package com.carlosjr.sfgbeerservice.web.controller;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
