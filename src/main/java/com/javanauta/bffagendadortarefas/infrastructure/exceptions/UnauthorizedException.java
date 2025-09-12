package com.javanauta.bffagendadortarefas.infrastructure.exceptions;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String menssage, Throwable throwable){
        super(menssage, throwable);
    }
}
