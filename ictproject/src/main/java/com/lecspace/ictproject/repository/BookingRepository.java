package com.lecspace.ictproject.repository;

import com.lecspace.ictproject.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByRoomId(Long roomId);
    List<Booking> findByUserId(Long userId);

    boolean existsByRoomIdAndBookingDateAndStartTimeLessThanAndEndTimeGreaterThanAndStatusIn(
            Long roomId, String bookingDate, String endTime, String startTime, List<String> status
    );


}
