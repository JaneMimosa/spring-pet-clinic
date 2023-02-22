package com.springframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
