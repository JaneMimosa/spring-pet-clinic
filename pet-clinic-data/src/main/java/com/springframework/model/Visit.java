package com.springframework.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "visits")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit extends BaseEntity{

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotEmpty
    private String description;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
