package com.spring.agular.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {

    BACK_END( "Back-end"),
    FRONT_END("Front-end");

    private final String value;

   private Category(String value){
       this.value = value;
   }

}
