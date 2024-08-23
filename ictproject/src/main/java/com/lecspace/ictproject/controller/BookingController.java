package com.lecspace.ictproject.controller;

import com.lecspace.ictproject.dto.BookingDTO;
import com.lecspace.ictproject.dto.CreateBookingRequestDTO;
import com.lecspace.ictproject.dto.ResponseDTO;
import com.lecspace.ictproject.dto.UpdateBookingRequestDTO;
import com.lecspace.ictproject.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseDTO<BookingDTO>> createBooking(@RequestBody CreateBookingRequestDTO request) {
        BookingDTO bookingDTO = bookingService.createBooking(request);
        return new ResponseEntity<>(new ResponseDTO<>(true, bookingDTO, "Booking created successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<BookingDTO>> getBookingById(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        return new ResponseEntity<>(new ResponseDTO<>(true, bookingDTO, "Booking retrieved successfully"), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<BookingDTO>>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(new ResponseDTO<>(true, bookings, "Bookings retrieved successfully"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<BookingDTO>> updateBooking(@PathVariable Long id, @RequestBody UpdateBookingRequestDTO request) {
        BookingDTO bookingDTO = bookingService.updateBooking(id, request);
        return new ResponseEntity<>(new ResponseDTO<>(true, bookingDTO, "Booking updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(new ResponseDTO<>(true, null, "Booking deleted successfully"), HttpStatus.NO_CONTENT);
    }
}
