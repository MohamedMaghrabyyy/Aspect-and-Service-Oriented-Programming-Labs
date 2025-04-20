package com.example.lab4.Room;

import com.example.lab4.Room.DTO.CreateRoomDTO;
import com.example.lab4.Room.DTO.UpdateRoomDTO;
import com.example.lab4.Room.Room;
import com.example.lab4.Room.RoomRepository;
import com.example.lab4.Annotations.DistributedLock;
import com.example.lab4.Redis.RedisClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ALL_ROOMS_CACHE_KEY = "rooms:all";
    private static final Duration CACHE_TTL = Duration.ofMinutes(10);
    private static final String ROOM_LOCK_PREFIX = "room";

    public List<Room> getAllRooms() {
        try {
            String cachedRooms = redisClient.get(ALL_ROOMS_CACHE_KEY);
            if (cachedRooms != null) {
                return objectMapper.readValue(cachedRooms, new TypeReference<List<Room>>() {});
            }
        } catch (Exception e) {
            // fallback to DB
        }

        List<Room> rooms = roomRepository.findAll();
        try {
            String roomsJson = objectMapper.writeValueAsString(rooms);
            redisClient.set(ALL_ROOMS_CACHE_KEY, roomsJson, CACHE_TTL);
        } catch (Exception e) {
            // cache store failed, no problem
        }

        return rooms;
    }

    public Room getRoomById(int id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room createRoom(CreateRoomDTO dto) {
        Room room = new Room();
        room.setNumber(dto.getNumber());
        room.setCapacity(dto.getCapacity());
        room.setPricePerNight(dto.getPricePerNight());
        room.setAvailable(dto.getIsAvailable());

        Room saved = roomRepository.save(room);

        // 🧹 Invalidate cache after create
        redisClient.delete(ALL_ROOMS_CACHE_KEY);

        return saved;
    }


    @DistributedLock(keyPrefix = ROOM_LOCK_PREFIX, keyIdentifierExpression = "#id", leaseTime = 120, timeUnit = TimeUnit.SECONDS)
    public Room updateRoom(int id, UpdateRoomDTO dto) {
        Room existingRoom = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));

        if (dto.getNumber() != null) existingRoom.setNumber(dto.getNumber());
        if (dto.getCapacity() != null) existingRoom.setCapacity(dto.getCapacity());
        if (dto.getPricePerNight() != null) existingRoom.setPricePerNight(dto.getPricePerNight());
        if (dto.getIsAvailable() != null) existingRoom.setAvailable(dto.getIsAvailable());

        Room updated = roomRepository.save(existingRoom);

        // 🧹 Invalidate cache after update
        redisClient.delete(ALL_ROOMS_CACHE_KEY);

        return updated;
    }

    public void deleteRoom(int id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);

        // 🧹 Invalidate cache after delete
        redisClient.delete(ALL_ROOMS_CACHE_KEY);
    }
}
