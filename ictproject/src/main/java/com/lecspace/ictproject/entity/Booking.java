//package com.lecspace.ictproject.entity;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="bookings")
//public class Booking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="booking_id", nullable = false, unique = true)
//    private Long id;
//
//    @Column(name="timeslot")
//    private String timeSlot;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name="status")
//    private BookingStatus status;
//
//    @Column(name="purpose")
//    private String purpose;
//
//    @ManyToOne
//    @JoinColumn(name="user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name="room_id", nullable = false)
//    private Room room;
//
//    public enum BookingStatus
//    {
//        PENDING,
//        CONFIRMED,
//        REJECTED,
//        CANCELLED
//    }
//}
//

package com.lecspace.ictproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "booking_date")
    private LocalDate bookingDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "status")
    private String status;


}


