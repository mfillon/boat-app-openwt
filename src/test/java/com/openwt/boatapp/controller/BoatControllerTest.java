package com.openwt.boatapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        when(repo.findById(1L)).thenReturn(Optional.of(boat1));
        when(repo.findById(4L)).thenReturn(Optional.empty());
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
    public void get_boats_from_different_owner_should_return_400() throws Exception {
        mvc.perform(get("/api/boats?owner=user1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser("user1")
    public void delete_boat_from_owner_should_succeed() throws Exception {
        mvc.perform(delete("/api/boats/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(repo).findById(1L);
        verify(repo).deleteById(1L);
    }

    @Test
    @WithMockUser("user1")
    public void delete_unexisting_boat_should_return_404() throws Exception {
        mvc.perform(delete("/api/boats/4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(repo).findById(4L);
    }

    @Test
    @WithMockUser("user2")
    public void delete_boat_from_different_owner_should_fail_with_400() throws Exception {
        mvc.perform(delete("/api/boats/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(repo).findById(1L);
    }

}
