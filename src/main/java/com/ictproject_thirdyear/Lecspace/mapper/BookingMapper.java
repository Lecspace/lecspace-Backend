package com.ictproject_thirdyear.Lecspace.mapper;

import com.ictproject_thirdyear.Lecspace.dto.BookingDTO;
import com.ictproject_thirdyear.Lecspace.entity.Booking;
import com.ictproject_thirdyear.Lecspace.entity.Room;
import com.ictproject_thirdyear.Lecspace.entity.User;

public class BookingMapper {

    public static BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setTimeSlot(booking.getTimeSlot());
        //bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setPurpose(booking.getPurpose());
        bookingDTO.setUserId(booking.getUser().getId());
        bookingDTO.setRoomId(booking.getRoom().getId());
        return bookingDTO;
    }

    public static Booking toEntity(BookingDTO bookingDTO, User user, Room room) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setTimeSlot(bookingDTO.getTimeSlot());
        //booking.setStatus(bookingDTO.getStatus());
        booking.setPurpose(bookingDTO.getPurpose());
        booking.setUser(user);
        booking.setRoom(room);
        return booking;
    }
}

