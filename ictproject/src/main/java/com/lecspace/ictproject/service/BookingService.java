/**package com.lecspace.ictproject.service;
import com.lecspace.ictproject.dto.BookingDTO;
import com.lecspace.ictproject.entity.Booking;
import com.lecspace.ictproject.entity.Room;
import com.lecspace.ictproject.entity.User;
import com.lecspace.ictproject.exception.ResourceNotFoundException;
import com.lecspace.ictproject.mapper.BookingMapper;
import com.lecspace.ictproject.repository.BookingRepository;
import com.lecspace.ictproject.repository.RoomRepository;
import com.lecspace.ictproject.repository.UserRepository;
import com.lecspace.ictproject.utils.BookingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private EmailService emailService;

    public BookingDTO createBooking(BookingDTO bookingDTO)
    {
        // Validate room and user existence
        Room room = roomRepository.findById(bookingDTO.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        User user = userRepository.findById(Math.toIntExact(bookingDTO.getUserId()))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Create booking entity
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking.setRoom(room);
        booking.setUser(user);
        booking.setStatus(Booking.BookingStatus.PENDING);

        // Save booking and return DTO
        Booking savedBooking = bookingRepository.save(booking);
        EmailService.sendEmail (user.getEmail(), "Booking Confirmation", "Your booking has been created.");
        return bookingMapper.toDTO(savedBooking);
    }

    public BookingDTO getBookingByUserId(Long id)
    {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingMapper.toDTO(booking);
    }

    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO)
    {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Update booking details
        existingBooking.setTimeSlot(bookingDTO.getTimeSlot());
        existingBooking.setStatus(BookingUtils.convertStringToStatus(bookingDTO.getStatus()));
        existingBooking.setPurpose(bookingDTO.getPurpose());

        // Save and return updated booking
        Booking updatedBooking = bookingRepository.save(existingBooking);
        return bookingMapper.toDTO(updatedBooking);
    }

    public void deleteBooking(Long id)
    {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        bookingRepository.delete(booking);
    }

    public void cancelBooking(Long id)
    {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Assuming that status is used to track cancellation
        booking.setStatus(Booking.BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // Notify user via email (add EmailService if not already present)
        EmailService.sendEmail(booking.getUser().getEmail(), "Booking Cancellation", "Your booking has been cancelled.");
    }
    public List<BookingDTO> getAllBookings()
    {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(bookingMapper::toDTO).collect(Collectors.toList());
    }

    public List<BookingDTO> getAllBookingsForDR()
    {
        // Implement role-based logic if needed
        return getAllBookings();
    }
    public BookingDTO updateBookingStatus(Long id, Booking.BookingStatus status)
    {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(status);
        Booking updatedBooking = bookingRepository.save(booking);

        // Notify user
        EmailService.sendEmail(booking.getUser().getEmail(), "Booking Status Update", "Your booking status has been updated to " + status);

        return bookingMapper.toDTO(updatedBooking);
    }
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream().map(bookingMapper::toDTO).collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByRoomId(Long roomId) {
        List<Booking> bookings = bookingRepository.findByRoomId(roomId);
        return bookings.stream().map(bookingMapper::toDTO).collect(Collectors.toList());
    }

}**/


package com.lecspace.ictproject.service;

import com.lecspace.ictproject.dto.BookingDTO;
import com.lecspace.ictproject.dto.CreateBookingRequestDTO;
import com.lecspace.ictproject.dto.UpdateBookingRequestDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(CreateBookingRequestDTO request);
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getAllBookings();
    BookingDTO updateBooking(Long id, UpdateBookingRequestDTO request);
    void deleteBooking(Long id);
}


