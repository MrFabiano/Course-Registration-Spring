package com.spring.agular.enums.convertes;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Status {

    ACTIVE("Ativo"),
    INACTIVE("Inativo");

    private final String value;

    private Status(String value){
        this.value = value;
    }
}
