package com.lecspace.ictproject.service;
//
//import com.lecspace.ictproject.dto.ResponseDTO;
//import com.lecspace.ictproject.dto.RoomDTO;
//import com.lecspace.ictproject.entity.Room;
//import com.lecspace.ictproject.exception.ResourceNotFoundException;
//import com.lecspace.ictproject.mapper.RoomMapper;
//import com.lecspace.ictproject.repository.RoomRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class RoomService {
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    public RoomDTO addRoom(RoomDTO roomDTO) {
//        Room room = RoomMapper.toEntity(roomDTO);
//        Room savedRoom = roomRepository.save(room);
//        return RoomMapper.toDTO(savedRoom);
//    }
//
//    public ResponseDTO<Room> createRoom(RoomDTO roomDTO) {
//        Room room = RoomMapper.toEntity(roomDTO);
//        Room savedRoom = roomRepository.save(room);
//        return new ResponseDTO<>(true, savedRoom, "Room created successfully");
//    }
//    public RoomDTO updateRoom(RoomDTO roomDTO) {
//        Room room = RoomMapper.toEntity(roomDTO);
//        Room updatedRoom = roomRepository.save(room);
//        return RoomMapper.toDTO(updatedRoom);
//    }
//
//    public void deleteRoom(Long id)
//    {
//        roomRepository.deleteById(id);
//    }
//
//    public RoomDTO getRoomById(Long id) {
//        Optional<Room> roomOpt = roomRepository.findById(id);
//        return roomOpt.map(RoomMapper::toDTO).orElse(null);
//    }
//
//    public List<RoomDTO> getAllRooms() {
//        List<Room> rooms = roomRepository.findAll();
//        return rooms.stream()
//                .map(RoomMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//    public List<String> getAvailableSlots(Long roomId, String date) {
//        Room room = roomRepository.findById(roomId)
//                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
//
//        // Assuming availableTimeSlots contains slots as comma-separated values
//        String[] slots = room.getAvailableTimeSlots().split(",");
//        // Filter slots based on the date (you may need to adjust this based on your implementation)
//        return Arrays.asList(slots);
//    }
//    // Find rooms by name
//    public List<Room> findRoomsByName(String name) {
//        return roomRepository.findByName(name);
//    }
//
//    // Find rooms by capacity
//    public List<Room> findRoomsByCapacity(int capacity) {
//        return roomRepository.findByCapacity(capacity);
//    }
//    // Find rooms that are available
//    public List<Room> findRoomsByAvailability(boolean isAvailable) {
//        return roomRepository.findByIsAvailable(isAvailable);
//    }
//
//    // Find rooms with a capacity greater than a specific value
//    public List<Room> findRoomsByCapacityGreaterThan(int capacity) {
//        return roomRepository.findByCapacityGreaterThan(capacity);
//    }
//
//    // Find rooms by a specific attribute, such as type or facility
//    public List<Room> findRoomsByType(String type) {
//        return roomRepository.findByType(type);
//    }
//
//
//}


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
        room.setId(1L);
        room.setName(request.getName());
        room.setCapacity(request.getCapacity());
        room.setAvailableTimeSlots(request.getAvailableTimeSlots());
        room.setType(request.getType());
        room.setFacilities(request.getFacilities());
        room = roomRepository.save(room);
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