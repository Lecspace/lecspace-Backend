package com.ictproject_thirdyear.Lecspace.dto;
import com.ictproject_thirdyear.Lecspace.entity.Booking.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private String timeSlot;
    private BookingStatus status;
    private String purpose;
    private Long userId;
    private Long roomId;


}
