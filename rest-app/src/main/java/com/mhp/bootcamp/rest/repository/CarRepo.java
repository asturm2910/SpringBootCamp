package com.mhp.bootcamp.rest.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends CrudRepository<Car, Long> {
}
