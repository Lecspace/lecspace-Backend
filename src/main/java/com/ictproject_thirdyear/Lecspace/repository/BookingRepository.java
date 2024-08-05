package com.ictproject_thirdyear.Lecspace.repository;

import com.ictproject_thirdyear.Lecspace.entity.Booking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_Id(Long userId);

    List<Booking> findByStatus(String status);
}
