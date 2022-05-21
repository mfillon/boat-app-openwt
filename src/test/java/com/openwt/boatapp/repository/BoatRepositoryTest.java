package com.openwt.boatapp.repository;

import com.openwt.boatapp.model.Boat;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BoatRepositoryTest {

    @Autowired
    BoatRepository boatRepo;

    @BeforeEach
    void setup() {
        Boat boat1 = new Boat(1L, "Matt", "Titanic");
        boatRepo.save(boat1);
    }

    @Test
    void testFindByOwner() {
        Iterable<Boat> mattsBoats = boatRepo.findByOwner("Matt");
        assertThat(mattsBoats).hasSize(1)
            .extracting("name").contains("Titanic");
    }

    @Test
    void testFindById() {
        Optional<Boat> boat1 = boatRepo.findById(1L);
        assertThat(boat1).isNotEmpty().get().hasFieldOrPropertyWithValue("name", "Titanic");
        
        Optional<Boat> boat2 = boatRepo.findById(2L);
        assertThat(boat2).isEmpty();
    }
}
