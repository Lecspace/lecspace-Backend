package com.lecspace.ictproject.dto;
import com.lecspace.ictproject.entity.Room;
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

