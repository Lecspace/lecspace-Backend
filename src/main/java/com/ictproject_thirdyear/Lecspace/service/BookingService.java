package com.ictproject_thirdyear.Lecspace.service;
import java.util.List;
import com.ictproject_thirdyear.Lecspace.entity.Booking;
import com.ictproject_thirdyear.Lecspace.repository.BookingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public BookingService() {
    }

    public Booking addBooking(Booking booking) {
        return (Booking)this.bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return this.bookingRepository.findByUser_Id(userId);
    }

    public void cancelBooking(Long id) {
        this.bookingRepository.deleteById(id);
    }
}
