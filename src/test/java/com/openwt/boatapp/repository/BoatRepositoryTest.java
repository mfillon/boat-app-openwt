package com.openwt.boatapp.repository;

import com.openwt.boatapp.model.Boat;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
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
        Boat boat2 = new Boat(2L, "John", "La Mouette");
        boatRepo.saveAll(Arrays.asList(boat1, boat2));
    }

    @Test
    void testFindByOwner() {
        Iterable<Boat> mattsBoats = boatRepo.findByOwner("Matt");
        assertThat(mattsBoats).hasSize(1)
            .extracting("name").contains("Titanic");
    }

    @Test
    void testFindById() {
        Optional<Boat> boat2 = boatRepo.findById(2L);
        assertThat(boat2).isNotEmpty().get().hasFieldOrPropertyWithValue("name", "La Mouette");
        
        Optional<Boat> boat3 = boatRepo.findById(999L);
        assertThat(boat3).isEmpty();
    }
}
