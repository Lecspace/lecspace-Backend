

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


    @Column(name = "booking_id")


    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "booking_date")

    private String bookingDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;


    @Column(name = "status")
    private String status;

    // New fields added
    @Column(name = "subject")
    private String subjectName;

    @Column(name = "capacity")
    private Integer StudentsCount;

    @Column(name = "note")
    private String note;

    @Column(name = "booked_by")
    private String userName;


}


