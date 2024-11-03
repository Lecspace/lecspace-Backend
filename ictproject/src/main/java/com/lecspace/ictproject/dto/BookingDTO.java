package com.lecspace.ictproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long roomId;
    private Long userId;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private String status; // e.g., PENDING, CONFIRMED

    private String subjectName;  // Updated to match entity
    private Integer studentsCount; // Updated to match entity
    private String note;
    private String userName;
}
