package com.carlosjr.sfgbeerproject.web.controller;

import com.carlosjr.sfgbeerproject.web.model.BeerDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    private static final String BASE_URL  = "/api/v1/beer";
    private String beerDtoJson;

    @BeforeEach
    public void setUp() throws JsonProcessingException {
        BeerDto beerDto = BeerDto.builder().build();
        beerDtoJson = objectMapper.writeValueAsString(beerDto);
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(String.format("%s/%s", BASE_URL, UUID.randomUUID().toString()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    void saveNewBeer() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }
    @Test
    void updateBeerId() throws Exception {
        mockMvc.perform(put(String.format("%s/%s", BASE_URL, UUID.randomUUID().toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}