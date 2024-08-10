package com.ictproject_thirdyear.Lecspace.repository;

import com.ictproject_thirdyear.Lecspace.entity.Booking;
import java.util.List;

import com.ictproject_thirdyear.Lecspace.entity.Room;
import com.ictproject_thirdyear.Lecspace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser_Id(Long userId);

    List<Booking> findByStatus(String status);
}
*/


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomAndTimeSlot(Room room, String timeSlot);
    List<Booking> findByUser(User user);
}
