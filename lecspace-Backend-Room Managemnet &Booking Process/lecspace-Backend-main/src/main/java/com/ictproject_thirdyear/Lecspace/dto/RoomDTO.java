package com.ictproject_thirdyear.Lecspace.dto;
import com.ictproject_thirdyear.Lecspace.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Long id;
    private String name;
    private int capacity;
    private String availableTimeSlots;
    private Room.RoomType type;  // LAB, LECTURE_HALL
    private String facilities;
}
