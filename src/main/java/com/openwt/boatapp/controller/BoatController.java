package com.openwt.boatapp.controller;

import com.openwt.boatapp.model.Boat;
import com.openwt.boatapp.repository.BoatRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/boats")
public class BoatController {
    
    private BoatRepository repository;

    public BoatController(BoatRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Boat> getBoats(@RequestParam String owner) {
        //TODO check if requested owner is authenticated user
        return repository.findByOwner(owner);
    }
}
