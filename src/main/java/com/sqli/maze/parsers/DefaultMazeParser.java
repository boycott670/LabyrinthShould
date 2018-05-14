package com.sqli.maze.parsers;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sqli.maze.Door;
import com.sqli.maze.Room;

public final class DefaultMazeParser implements MazeParser
{
  
  private static final String NORMAL_DOOR = "|";
  private static final String SENSOR_DOOR = "$";

  @Override
  public Set<Room> parseRooms(String[] maze)
  {
    return Arrays.stream(maze)
        .map(mazeEntry -> mazeEntry.split(String.format("[%s%s]", NORMAL_DOOR, SENSOR_DOOR)))
        .flatMap(Arrays::stream)
        .map(Room::new)
        .collect(Collectors.toSet());
  }

  @Override
  public Set<Room> parseDoors(Set<Room> rooms, String[] maze)
  {
    final Map<String, Room> roomsMapping = rooms.stream()
        .collect(Collectors.toMap(Room::getLabel, Function.identity()));
    
    for (final String mazeEntry : maze)
    {
      final String[] mazeEntryTokens = mazeEntry.split("");
      
      final Room leftAdjacentRoom = roomsMapping.get(mazeEntryTokens[0]);
      final Room rightAdjacentRoom = roomsMapping.get(mazeEntryTokens[2]);
      
      final Door door = new Door(leftAdjacentRoom, rightAdjacentRoom, Objects.equals(SENSOR_DOOR, mazeEntryTokens[1]));
      
      leftAdjacentRoom.addAdjacentDoor(door);
      rightAdjacentRoom.addAdjacentDoor(door);
    }
    
    return rooms;
  }

}
