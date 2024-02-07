package com.spring.agular.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String id) {
        super("Registro não encontrado com o id: " + id);
    }
}
