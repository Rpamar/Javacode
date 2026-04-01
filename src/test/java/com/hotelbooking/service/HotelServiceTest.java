package com.hotelbooking.service;

import com.hotelbooking.entity.Hotel;
import com.hotelbooking.repository.HotelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    public void testGetAllHotels() {
        Hotel hotel1 = new Hotel("Hotel A", "Location A");
        Hotel hotel2 = new Hotel("Hotel B", "Location B");
        List<Hotel> hotels = Arrays.asList(hotel1, hotel2);

        when(hotelRepository.findAll()).thenReturn(hotels);

        List<Hotel> result = hotelService.getAllHotels();

        assertEquals(2, result.size());
        assertEquals("Hotel A", result.get(0).getName());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    public void testGetHotelById() {
        Hotel hotel = new Hotel("Hotel A", "Location A");
        hotel.setId(1L);

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        Optional<Hotel> result = hotelService.getHotelById(1L);

        assertTrue(result.isPresent());
        assertEquals("Hotel A", result.get().getName());
        verify(hotelRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveHotel() {
        Hotel hotel = new Hotel("Hotel A", "Location A");

        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel result = hotelService.saveHotel(hotel);

        assertEquals("Hotel A", result.getName());
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    public void testDeleteHotel() {
        doNothing().when(hotelRepository).deleteById(1L);

        hotelService.deleteHotel(1L);

        verify(hotelRepository, times(1)).deleteById(1L);
    }
}