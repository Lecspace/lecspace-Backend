package com.lecspace.ictproject.controller;

import com.lecspace.ictproject.dto.RoomDTO;
import com.lecspace.ictproject.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDTO> addRoom(@RequestBody RoomDTO roomDTO) {
        RoomDTO savedRoom = roomService.addRoom(roomDTO);
        return ResponseEntity.ok(savedRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        roomDTO.setId(id);
        RoomDTO updatedRoom = roomService.updateRoom(roomDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        RoomDTO roomDTO = roomService.getRoomById(id);
        return ResponseEntity.ok(roomDTO);
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        List<RoomDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }
}
