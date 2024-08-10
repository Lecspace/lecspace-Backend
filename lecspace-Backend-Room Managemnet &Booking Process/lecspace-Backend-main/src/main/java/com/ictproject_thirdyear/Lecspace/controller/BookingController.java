package com.ictproject_thirdyear.Lecspace.controller;

import com.ictproject_thirdyear.Lecspace.dto.BookingDTO;
import com.ictproject_thirdyear.Lecspace.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        if (createdBooking != null) {
            return ResponseEntity.ok(createdBooking);
        } else {
            return ResponseEntity.badRequest().build(); // Or any other error handling
        }
    }

    @PostMapping("/approve/{bookingId}")
    public ResponseEntity<BookingDTO> approveBooking(@PathVariable Long bookingId) {
        BookingDTO approvedBooking = bookingService.approveBooking(bookingId);
        if (approvedBooking != null) {
            return ResponseEntity.ok(approvedBooking);
        } else {
            return ResponseEntity.badRequest().build(); // Or any other error handling
        }
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

}
