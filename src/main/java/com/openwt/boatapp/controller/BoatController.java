package com.openwt.boatapp.controller;

import java.security.Principal;
import java.util.Optional;

import com.openwt.boatapp.model.Boat;
import com.openwt.boatapp.repository.BoatRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/boats")
@Slf4j
public class BoatController {

    private BoatRepository repository;

    public BoatController(BoatRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Boat> getBoats(@RequestParam String owner, Principal principal) {
        if (!owner.equals(principal.getName())) {
            log.warn("Requested owner is different than logged in user");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Requested owner is different than logged in user");
        }
        return repository.findByOwner(owner);
    }

    @DeleteMapping("/{boatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBoat(@PathVariable long boatId, Principal principal) {
        log.debug("Delete boat request for boat ID # {}", boatId);

        Optional<Boat> boat = repository.findById(boatId);
        if (boat.isEmpty()) {
            log.debug("Boat with ID #{} not found", boatId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        String owner = boat.get().getOwner();
        if (!owner.equals(principal.getName())) {
            log.warn("Boat owner is different than logged in user");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Boat owner is different than logged in user");
        }
        repository.deleteById(boatId);
    }

    @PutMapping("/{boatId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBoat(@PathVariable long boatId, @RequestBody Boat updatedBoat, Principal principal) {

        //TODO extract validate method for all endpoints
        Optional<Boat> boat = repository.findById(boatId);
        if (boat.isEmpty()) {
            log.debug("Boat with ID #{} not found", boatId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Boat boatToUpdate = boat.get();
        String owner = boatToUpdate.getOwner();
        if (!owner.equals(principal.getName())) {
            log.warn("Boat owner is different than logged in user");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Boat owner is different than logged in user");
        }

        boatToUpdate.setName(updatedBoat.getName());
        boatToUpdate.setDescription(updatedBoat.getDescription());

        repository.save(boatToUpdate);
    }

}
