package com.springframework.services;

import com.springframework.model.Vet;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface VetService extends CrudRepository<Vet, Long> {

}
