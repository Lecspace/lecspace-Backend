package com.lecspace.ictproject.repository;


import com.lecspace.ictproject.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}

