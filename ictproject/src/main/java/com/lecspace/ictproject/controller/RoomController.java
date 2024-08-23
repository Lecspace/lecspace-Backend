package com.lecspace.ictproject.controller;

import com.lecspace.ictproject.dto.ResponseDTO;
import com.lecspace.ictproject.dto.RoomCreateDTO;
import com.lecspace.ictproject.dto.RoomDTO;
import com.lecspace.ictproject.dto.RoomUpdateDTO;
import com.lecspace.ictproject.entity.Room;
import com.lecspace.ictproject.exception.CustomException;
import com.lecspace.ictproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
//    @GetMapping("/test")
//    public String test(){
//        return "Meka wadada";
//    }
//    @PostMapping
//    public ResponseEntity<ResponseDTO<Room>> createRoom(@RequestBody RoomDTO room) {
//        return new ResponseEntity<>(roomService.createRoom(room), HttpStatus.CREATED);
//    }
//    @PostMapping("/add")
//    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
//       RoomDTO savedRoom = roomService.addRoom(roomDTO);
//       return ResponseEntity.ok(savedRoom);
//
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
//      roomDTO.setId(id);
//      RoomDTO updatedRoom = roomService.updateRoom(roomDTO);
//      return ResponseEntity.ok(updatedRoom);
//
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
//        roomService.deleteRoom(id);
//        return ResponseEntity.noContent().build();
//
//
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
//      RoomDTO roomDTO = roomService.getRoomById(id);
//      return ResponseEntity.ok(roomDTO);
//
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<List<RoomDTO>> getAllRooms()
//    {
//        List<RoomDTO> rooms = roomService.getAllRooms();
//       return ResponseEntity.ok(rooms);
//
//    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Room>> createRoom(@RequestBody RoomCreateDTO request) {
        try {
            Room roomDTO = roomService.createRoom(request);
            return ResponseEntity.ok(new ResponseDTO<>(true, roomDTO, "Room created successfully"));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO<>(false, null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(false, null, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO<RoomDTO>> updateRoom(@RequestBody RoomUpdateDTO request) {
        try {
            RoomDTO roomDTO = roomService.updateRoom(request);
            return ResponseEntity.ok(new ResponseDTO<>(true, roomDTO, "Room updated successfully"));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(false, null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(false, null, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deleteRoom(@PathVariable Long id) {
        try {
            roomService.deleteRoom(id);
            return ResponseEntity.ok(new ResponseDTO<>(true, null, "Room deleted successfully"));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(false, null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(false, null, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<RoomDTO>> getRoomById(@PathVariable Long id) {
        try {
            RoomDTO roomDTO = roomService.getRoomById(id);
            return ResponseEntity.ok(new ResponseDTO<>(true, roomDTO, "Room retrieved successfully"));
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO<>(false, null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(false, null, e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<RoomDTO>>> getAllRooms() {
        try {
            List<RoomDTO> rooms = roomService.getAllRooms();
            return ResponseEntity.ok(new ResponseDTO<>(true, rooms, "Rooms retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO<>(false, null, e.getMessage()));
        }
    }
}
