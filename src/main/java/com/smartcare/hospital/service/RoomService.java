package com.smartcare.hospital.service;

import com.smartcare.hospital.entity.Room;
import java.util.List;

public interface RoomService {
    Room addRoom(Room room);
    List<Room> getAllRooms();
    Room getRoomById(Integer id);
    Room updateRoomAvailability(Integer id, Boolean isAvailable);
}