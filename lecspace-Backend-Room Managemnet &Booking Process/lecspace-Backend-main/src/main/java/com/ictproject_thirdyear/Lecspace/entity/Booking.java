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
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id", nullable = false, unique = true)
    private Long id;

    @Column(name="timeslot")
    private String timeSlot;
    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        REJECTED,
        CANCELLED
    }
    @Column(name="status")
    private String status;

    @Column(name="purpose")
    private String purpose;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="room_id", nullable = false)
    private Room room;
}
