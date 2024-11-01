package com.lecspace.ictproject.service;
import com.lecspace.ictproject.dto.RoomCreateDTO;
import com.lecspace.ictproject.dto.RoomDTO;
import com.lecspace.ictproject.dto.RoomUpdateDTO;
import com.lecspace.ictproject.entity.Room;
import com.lecspace.ictproject.exception.CustomException;
import com.lecspace.ictproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public Room createRoom(RoomCreateDTO request) {
        Room room = new Room();
        room.setName(request.getName());
        room.setCapacity(request.getCapacity());
        room.setAvailableTimeSlots(request.getAvailableTimeSlots());
        room.setType(request.getType());
        room.setFacilities(request.getFacilities());
        room = roomRepository.save(room);  // The ID will be auto-generated
        return room;
    }

    public RoomDTO updateRoom(RoomUpdateDTO request) {
        Room room = roomRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Room not found"));

        room.setName(request.getName());
        room.setCapacity(request.getCapacity());
        room.setAvailableTimeSlots(request.getAvailableTimeSlots());
        room.setType(request.getType());
        room.setFacilities(request.getFacilities());

        room = roomRepository.save(room);
        return convertToDTO(room);
    }

    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new CustomException("Room not found"));

        roomRepository.delete(room);
    }

    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new CustomException("Room not found"));
        return convertToDTO(room);
    }

    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private RoomDTO convertToDTO(Room room) {
        return new RoomDTO(
                room.getId(),
                room.getName(),
                room.getCapacity(),
                room.getAvailableTimeSlots(),
                room.getType(),
                room.getFacilities()
        );
    }

}