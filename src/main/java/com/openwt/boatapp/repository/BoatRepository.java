package com.openwt.boatapp.repository;

import com.openwt.boatapp.model.Boat;

import org.springframework.data.repository.CrudRepository;

public interface BoatRepository extends CrudRepository<Boat, Long> {
    Iterable<Boat> findByOwner(String owner);
}
