package com.springframework.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseEntity{

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public Person(Long id, String firstName, String lastName) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
