package com.lecspace.ictproject.mapper;

import com.lecspace.ictproject.dto.BookingDTO;
import com.lecspace.ictproject.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setRoomId(booking.getRoomId());
        bookingDTO.setUserId(booking.getUserId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStatus(booking.getStatus());
        return bookingDTO;
    }

    public Booking toEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setRoomId(bookingDTO.getRoomId());
        booking.setUserId(bookingDTO.getUserId());
        booking.setBookingDate(bookingDTO.getBookingDate());
        booking.setStartTime(bookingDTO.getStartTime());
        booking.setEndTime(bookingDTO.getEndTime());
        booking.setStatus(bookingDTO.getStatus());
        return booking;
    }
}
