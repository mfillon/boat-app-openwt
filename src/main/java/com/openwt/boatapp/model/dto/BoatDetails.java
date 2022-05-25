package com.openwt.boatapp.model.dto;

import com.openwt.boatapp.model.Boat;

import lombok.Data;

@Data
public class BoatDetails {
    private final String name;
    private final String description;

    public Boat toBoat() {
        Boat boat = new Boat();
        boat.setName(name);
        boat.setDescription(description);
        return boat;
    }
}
