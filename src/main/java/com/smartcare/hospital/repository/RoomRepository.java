package com.smartcare.hospital.repository;

import com.smartcare.hospital.entity.Room;
import com.smartcare.hospital.enums.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    /**
     * Find a room by its unique room number.
     */
    Optional<Room> findByRoomNumber(String roomNumber);

    /**
     * Get all rooms filtered by availability status.
     */
    List<Room> findByIsAvailable(Boolean isAvailable);

    /**
     * Find available rooms filtered by room category (General_Ward, Private_Room, ICU).
     */
    List<Room> findByRoomTypeAndIsAvailable(RoomType roomType, Boolean isAvailable);
}