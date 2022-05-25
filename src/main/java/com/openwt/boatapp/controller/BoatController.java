package com.openwt.boatapp.controller;

import java.security.Principal;

import com.openwt.boatapp.model.Boat;
import com.openwt.boatapp.repository.BoatRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}
