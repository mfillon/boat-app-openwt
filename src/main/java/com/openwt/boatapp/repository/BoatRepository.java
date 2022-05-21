package com.openwt.boatapp.repository;

import java.util.Optional;
import java.util.Set;

import com.openwt.boatapp.model.Boat;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * 
 */
@RepositoryRestResource(path = "boats")
public interface BoatRepository extends Repository<Boat, Long> {

    Iterable<Boat> findAll();

    Iterable<Boat> findByOwner(@Param("owner") String owner);

    Optional<Boat> findById(Long id);

    Boat save(Boat boat);

    void delete(Boat boatToDelete);
}
