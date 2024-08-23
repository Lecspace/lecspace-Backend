package com.lecspace.ictproject.repository;


import com.lecspace.ictproject.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long>
{
    // Find rooms by name
    List<Room> findByName(String name);

    // Find rooms by capacity
    List<Room> findByCapacity(int capacity);

    // Find rooms that are available
    //List<Room> findByIsAvailable(boolean isAvailable);


    // Find rooms with a capacity greater than a specific value
    List<Room> findByCapacityGreaterThan(int capacity);

    // Find rooms by a specific attribute, such as type or facility
    List<Room> findByType(String type);
    List<Room> findByIsAvailable(boolean isAvailable);
}





