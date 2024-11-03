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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
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
//    public BookingDTO createBooking(CreateBookingRequestDTO request) {
//        Room room = roomRepository.findById(request.getRoomId())
//                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        if(room.getCapacity()>=request.getCapacity()) {
//
//        }
//        Booking booking = new Booking();
//        booking.setRoomId(room.getId());
//        booking.setUserId(Long.valueOf(user.getId()));
//        booking.setBookingDate(request.getBookingDate());
//        booking.setStartTime(request.getStartTime());
//        booking.setEndTime(request.getEndTime());
//        booking.setStatus("PENDING");
//
//        Booking savedBooking = bookingRepository.save(booking);
//        return bookingMapper.toDTO(savedBooking);
//    }
    public BookingDTO createBooking(CreateBookingRequestDTO request) {
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Check room capacity
        if (request.getStudentsCount()  > room.getCapacity()) {
            throw new ResourceNotFoundException("Requested capacity exceeds room capacity");
        }

        // Check for conflicting bookings
        boolean isRoomAvailable = !bookingRepository.existsByRoomIdAndBookingDateAndStartTimeLessThanAndEndTimeGreaterThanAndStatusIn(
                request.getRoomId(),
                request.getBookingDate(),
                request.getEndTime(),
                request.getStartTime(),
                Arrays.asList("PENDING")

        );
        if (!isRoomAvailable) {
            System.out.println("work");

            throw new ResourceNotFoundException("Room is already booked for the specified date and time.");
        }

        // Create and save the booking if no conflicts
        Booking booking = new Booking();
        booking.setRoomId(room.getId());
        booking.setUserId(Long.valueOf(user.getId()));
        booking.setBookingDate(request.getBookingDate());
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setStatus("PENDING");
        booking.setSubjectName(request.getSubjectName());
        booking.setStudentsCount(request.getStudentsCount());
        booking.setNote(request.getNote());
        booking.setUserName(request.getUserName());
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
        existingBooking.setBookingDate(String.valueOf(request.getBookingDate()));
        existingBooking.setStartTime(String.valueOf(request.getStartTime()));
        existingBooking.setEndTime(String.valueOf(request.getEndTime()));
        existingBooking.setStatus(request.getStatus());

        // Update new fields
        existingBooking.setSubjectName(request.getSubjectName());
        existingBooking.setStudentsCount(request.getStudentsCount());
        existingBooking.setNote(request.getNote());
        existingBooking.setUserName(request.getUserName());

        Booking updatedBooking = bookingRepository.save(existingBooking);
        return bookingMapper.toDTO(updatedBooking);
    }


    @Override
    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }

    // New implementations
    @Override
    public List<BookingDTO> getBookingsByRoomId(Long roomId) {
        List<Booking> bookings = bookingRepository.findByRoomId(roomId);
        return bookings.stream().map(bookingMapper::toDTO).collect(Collectors.toList());

    }
    @Override
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(bookingMapper::toDTO).collect(Collectors.toList());
    }
}



