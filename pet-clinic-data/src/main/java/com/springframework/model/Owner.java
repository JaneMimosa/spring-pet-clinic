package com.springframework.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends Person{

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city,
                 String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;

        if(pets != null) {
            this.pets = pets;
        }
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @NotEmpty
    private String address;
    @NotEmpty
    private String city;
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    public Pet getPet(String name) {
        return getPet(name, false);
    }

    public void addPet(Pet pet) {
        if (pet.isNew()) {
            getPets().add(pet);
        }
    }

    public Pet getPet(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for(Pet pet : pets) {
            if(!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }
}
