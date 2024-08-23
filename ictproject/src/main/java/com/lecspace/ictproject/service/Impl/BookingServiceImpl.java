package com.lecspace.ictproject.service.Impl;
import com.lecspace.ictproject.dto.BookingDTO;
import com.lecspace.ictproject.dto.CreateBookingRequestDTO;
import com.lecspace.ictproject.dto.UpdateBookingRequestDTO;
import com.lecspace.ictproject.entity.Booking;
import com.lecspace.ictproject.entity.Room;
import com.lecspace.ictproject.entity.User;
import com.lecspace.ictproject.exception.ResourceNotFoundException;
import com.lecspace.ictproject.mapper.BookingMapper;
import com.lecspace.ictproject.repository.BookingRepository;
import com.lecspace.ictproject.repository.RoomRepository;
import com.lecspace.ictproject.repository.UserRepository;
import com.lecspace.ictproject.service.BookingService;
//import com.lecspace.ictproject.utils.BookingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public BookingDTO createBooking(CreateBookingRequestDTO request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Booking booking = new Booking();
        booking.setRoomId(room.getId());
        booking.setUserId(Long.valueOf(user.getId()));
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus("PENDING");

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDTO(savedBooking);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingMapper.toDTO(booking);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookingDTO updateBooking(Long id, UpdateBookingRequestDTO request) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        existingBooking.setRoomId(request.getRoomId());
        existingBooking.setUserId(request.getUserId());
        existingBooking.setBookingDate(request.getBookingDate());
        existingBooking.setStartTime(request.getStartTime());
        existingBooking.setEndTime(request.getEndTime());
        existingBooking.setStatus(request.getStatus());

        Booking updatedBooking = bookingRepository.save(existingBooking);
        return bookingMapper.toDTO(updatedBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }
}

