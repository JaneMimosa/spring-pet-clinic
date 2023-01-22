package com.springframework.services;

import com.springframework.model.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PetService extends CrudRepository<Pet, Long>  {

}
