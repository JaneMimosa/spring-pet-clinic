package com.springframework.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pet_types")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PetType extends BaseEntity {
    @NotEmpty
    private String name;

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
