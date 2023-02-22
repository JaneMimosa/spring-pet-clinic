package com.springframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "specialties")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Speciality extends BaseEntity{

    @NotEmpty
    private String description;

}
