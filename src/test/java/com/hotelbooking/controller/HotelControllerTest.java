package com.hotelbooking.controller;

import com.hotelbooking.entity.Hotel;
import com.hotelbooking.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @Test
    public void testGetAllHotels() throws Exception {
        Hotel hotel1 = new Hotel("Hotel A", "Location A");
        Hotel hotel2 = new Hotel("Hotel B", "Location B");
        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);

        when(hotelService.getAllHotels()).thenReturn(hotels);

        mockMvc.perform(get("/api/hotels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Hotel A"));
    }

    @Test
    public void testGetHotelById() throws Exception {
        Hotel hotel = new Hotel("Hotel A", "Location A");
        hotel.setId(1L);

        when(hotelService.getHotelById(1L)).thenReturn(Optional.of(hotel));

        mockMvc.perform(get("/api/hotels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hotel A"));
    }

    @Test
    public void testCreateHotel() throws Exception {
        Hotel hotel = new Hotel("Hotel A", "Location A");

        when(hotelService.saveHotel(any(Hotel.class))).thenReturn(hotel);

        mockMvc.perform(post("/api/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Hotel A\",\"location\":\"Location A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hotel A"));
    }
}