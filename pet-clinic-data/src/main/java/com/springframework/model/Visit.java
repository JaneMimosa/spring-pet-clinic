package com.springframework.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit extends BaseEntity{

    private LocalDate date;
    private String description;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
