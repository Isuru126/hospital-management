package com.smartcare.hospital.controller;

import com.smartcare.hospital.entity.Room;
import com.smartcare.hospital.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room) {
        Room created = roomService.addRoom(room);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PutMapping("/{id}/availability")
    public ResponseEntity<Room> updateRoomAvailability(
            @PathVariable("id") Integer id,
            @RequestParam("isAvailable") Boolean isAvailable) {
        return ResponseEntity.ok(roomService.updateRoomAvailability(id, isAvailable));
    }
}