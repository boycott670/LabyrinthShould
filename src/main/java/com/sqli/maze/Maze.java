package com.sqli.maze;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sqli.maze.exceptions.IllegalMoveException;
import com.sqli.maze.parsers.DefaultMazeParser;
import com.sqli.maze.parsers.MazeParser;
import com.sqli.maze.visitors.rooms.RoomsVisitor;
import com.sqli.maze.visitors.rooms.SensorDoorRoomsVisitor;

final class Maze
{
  private final MazeParser parser;
  private final Map<String, Room> rooms;
  
  private Room currentRoom;
  private Room previousRoom;
  
  private final RoomsVisitor roomsVisitor;
  
  Maze(final String... maze)
  {
    parser = new DefaultMazeParser();
    
    rooms = parser.parseDoors(parser.parseRooms(maze), maze)
        .stream()
        .collect(Collectors.toMap(Room::getLabel, Function.identity()));
    
    roomsVisitor = new SensorDoorRoomsVisitor();
  }
  
  void popIn(final String room)
  {
    currentRoom = rooms.get(room);
    
    roomsVisitor.startIn(currentRoom);
  }
  
  String at()
  {
    return currentRoom.getLabel();
  }
  
  void walkTo(final String to)
  {
    previousRoom = currentRoom;
    
    currentRoom = Optional.ofNullable(rooms.get(to))
        .filter(currentRoom::walkTo)
        .orElseThrow(IllegalMoveException::new);
    
    roomsVisitor.walkTo(currentRoom);
  }
  
  void closeLastDoor()
  {
    currentRoom.closeLastDoor(previousRoom);
  }
  
  String readSensors()
  {
    return roomsVisitor.readPath();
  }
}
