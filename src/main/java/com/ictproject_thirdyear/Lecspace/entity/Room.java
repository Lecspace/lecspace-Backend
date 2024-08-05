package com.ictproject_thirdyear.Lecspace.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rooms")
public class Room {
    public enum RoomType {
        LAB,
        LECTURE_HALL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id", nullable = false, unique = true)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="capacity")
    private int capacity;

    @Column(name="available_time_slots")
    private String availableTimeSlots;  // Comma-separated time slots

    @Enumerated(EnumType.STRING)
    @Column(name="room_type")
    private RoomType type;  // LAB, LECTURE_HALL
}


