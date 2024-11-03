package com.lecspace.ictproject.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookingRequestDTO {
    private Long roomId;
    private int userId;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private String subjectName;  // Updated to match entity
    private Integer studentsCount; // Updated to match entity
    private String note;
    private String userName;
}