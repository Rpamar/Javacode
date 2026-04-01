package com.hotelbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private LocalDate checkIn;
    private LocalDate checkOut;
    @ManyToOne
    private Room room;

    public Booking() {}

    public Booking(String customerName, LocalDate checkIn, LocalDate checkOut, Room room) {
        this.customerName = customerName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.room = room;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
}