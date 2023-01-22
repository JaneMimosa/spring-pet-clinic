package com.springframework.services;

import com.springframework.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface OwnerService extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);
}
