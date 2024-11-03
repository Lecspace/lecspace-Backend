package com.lecspace.ictproject.mapper;

import com.lecspace.ictproject.dto.BookingDTO;
import com.lecspace.ictproject.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingMapper {

    public BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        //bookingDTO.setId(1L);
        bookingDTO.setId(booking.getId());
        bookingDTO.setRoomId(booking.getRoomId());
        bookingDTO.setUserId(booking.getUserId());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStatus(booking.getStatus());
// New fields added for mapping
        bookingDTO.setSubjectName(booking.getSubjectName());  // Updated to match entity
        bookingDTO.setStudentsCount(booking.getStudentsCount()); // Updated to match entity
        bookingDTO.setNote(booking.getNote());
        bookingDTO.setUserName(booking.getUserName());


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


        booking.setSubjectName(bookingDTO.getSubjectName());  // Updated to match entity
        booking.setStudentsCount(bookingDTO.getStudentsCount()); // Updated to match entity
        booking.setNote(bookingDTO.getNote());
        booking.setUserName(bookingDTO.getUserName());        // Updated to match entity

        return booking;
    }
}
