package com.smartcare.hospital.service.impl;

import com.smartcare.hospital.entity.Room;
import com.smartcare.hospital.exception.ResourceNotFoundException;
import com.smartcare.hospital.repository.RoomRepository;
import com.smartcare.hospital.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with id: " + id));
    }

    @Override
    public Room updateRoomAvailability(Integer id, Boolean isAvailable) {
        Room room = getRoomById(id);
        room.setIsAvailable(isAvailable);
        return roomRepository.save(room);
    }
}