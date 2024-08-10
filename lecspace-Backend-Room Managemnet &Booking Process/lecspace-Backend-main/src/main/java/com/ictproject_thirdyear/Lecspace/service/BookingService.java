package com.ictproject_thirdyear.Lecspace.service;

import com.ictproject_thirdyear.Lecspace.dto.BookingDTO;
import com.ictproject_thirdyear.Lecspace.entity.Booking;
import com.ictproject_thirdyear.Lecspace.entity.Room;
import com.ictproject_thirdyear.Lecspace.entity.User;
import com.ictproject_thirdyear.Lecspace.mapper.BookingMapper;
import com.ictproject_thirdyear.Lecspace.repository.BookingRepository;
import com.ictproject_thirdyear.Lecspace.repository.RoomRepository;
import com.ictproject_thirdyear.Lecspace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Optional<User> userOptional = userRepository.findById(bookingDTO.getUserId());
        Optional<Room> roomOptional = roomRepository.findById(bookingDTO.getRoomId());

        if (userOptional.isPresent() && roomOptional.isPresent()) {
            Booking booking = BookingMapper.toEntity(bookingDTO, userOptional.get(), roomOptional.get());
            booking.setStatus("PENDING"); // Default status

            bookingRepository.save(booking);
            return BookingMapper.toDTO(booking);
        } else {
            return null; // Handle error when user or room is not found
        }
    }

    public BookingDTO approveBooking(Long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

        if (bookingOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            booking.setStatus("CONFIRMED");
            bookingRepository.save(booking);

            // Send email notification
            User user = booking.getUser();
            String subject = "Booking Approved";
            String body = "Dear " + user.getName() + ",\n\nYour booking for " + booking.getRoom().getName() +
                    " has been approved.\n\nTime Slot: " + booking.getTimeSlot() + "\nPurpose: " + booking.getPurpose();
            emailService.sendEmail(user.getEmail(), subject, body);

            return BookingMapper.toDTO(booking);
        } else {
            return null; // Handle case when booking is not found
        }
    }

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingMapper::toDTO).toList();
    }

    // Other methods...
}
