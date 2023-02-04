package com.springframework.model;

import jakarta.persistence.MappedSuperclass;
import lombok.*;

@MappedSuperclass
@Setter
@Getter
public class Person extends BaseEntity{

    private String firstName;
    private String lastName;
}
