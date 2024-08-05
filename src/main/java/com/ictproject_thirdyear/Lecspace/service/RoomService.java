package com.ictproject_thirdyear.Lecspace.service;
import java.util.List;
import com.ictproject_thirdyear.Lecspace.entity.Room;
import com.ictproject_thirdyear.Lecspace.repository.RoomRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomService() {
    }

    public Room addRoom(Room room) {
        return (Room)this.roomRepository.save(room);
    }

    public List<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    public Room updateRoom(Room room) {
        return (Room)this.roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        this.roomRepository.deleteById(id);
    }
}
