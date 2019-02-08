package com.mhp.bootcamp.helloworld.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
public class Name {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String firstname;

}
