package com.openwt.boatapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.openwt.boatapp.model.Boat;
import com.openwt.boatapp.repository.BoatRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BoatController.class)
public class BoatControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BoatRepository repo;

    @BeforeEach
    public void setup() {
        Boat boat1 = new Boat(1L, "user1", "boat1");
        Boat boat2 = new Boat(2L, "user1", "boat2");
        List<Boat> boats = Arrays.asList(boat1, boat2);
        when(repo.findByOwner("user1")).thenReturn(boats);
    }

    @Test
    @WithMockUser("user1")
    public void test_get_boat_by_user() throws Exception {
        mvc.perform(get("/api/boats?owner=user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("boat1")))
                .andExpect(jsonPath("$[1].name", is("boat2")));
    }

    @Test
    @WithMockUser("user2")
    public void shouldReturn400ForAuthenticatedUserDifferentOwner() throws Exception {
        mvc.perform(get("/api/boats?owner=user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
