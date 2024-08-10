package com.ictproject_thirdyear.Lecspace.service;
import com.ictproject_thirdyear.Lecspace.dto.RoomDTO;
import com.ictproject_thirdyear.Lecspace.entity.Room;
import com.ictproject_thirdyear.Lecspace.mapper.RoomMapper;
import com.ictproject_thirdyear.Lecspace.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public RoomDTO addRoom(RoomDTO roomDTO) {
        Room room = RoomMapper.toEntity(roomDTO);
        Room savedRoom = roomRepository.save(room);
        return RoomMapper.toDTO(savedRoom);
    }

    public RoomDTO updateRoom(RoomDTO roomDTO) {
        Room room = RoomMapper.toEntity(roomDTO);
        Room updatedRoom = roomRepository.save(room);
        return RoomMapper.toDTO(updatedRoom);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public RoomDTO getRoomById(Long id) {
        Optional<Room> roomOpt = roomRepository.findById(id);
        return roomOpt.map(RoomMapper::toDTO).orElse(null);
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(RoomMapper::toDTO)
                .collect(Collectors.toList());
    }
}
