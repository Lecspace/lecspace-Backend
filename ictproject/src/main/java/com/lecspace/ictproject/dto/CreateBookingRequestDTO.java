package com.lecspace.ictproject.dto;
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
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
}