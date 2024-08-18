package com.lecspace.ictproject.mapper;


import com.lecspace.ictproject.dto.RoomDTO;
import com.lecspace.ictproject.entity.Room;
import org.springframework.stereotype.Component;

@Component


public class RoomMapper {

    public static RoomDTO toDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setName(room.getName());
        roomDTO.setCapacity(room.getCapacity());
        roomDTO.setAvailableTimeSlots(room.getAvailableTimeSlots());
        roomDTO.setType(room.getType());
        roomDTO.setFacilities(room.getFacilities());
        return roomDTO;
    }

    public static Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setName(roomDTO.getName());
        room.setCapacity(roomDTO.getCapacity());
        room.setAvailableTimeSlots(roomDTO.getAvailableTimeSlots());
        room.setType(roomDTO.getType());
        room.setFacilities(roomDTO.getFacilities());
        return room;
    }
}
