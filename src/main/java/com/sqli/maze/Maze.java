package com.sqli.maze;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.sqli.maze.exceptions.IllegalMoveException;
import com.sqli.maze.parsers.DefaultMazeParser;
import com.sqli.maze.parsers.MazeParser;

final class Maze
{
  private final MazeParser parser;
  private final Map<String, Room> rooms;
  
  private Room currentRoom;
  
  Maze(final String... maze)
  {
    parser = new DefaultMazeParser();
    
    rooms = parser.parseDoors(parser.parseRooms(maze), maze)
        .stream()
        .collect(Collectors.toMap(Room::getLabel, Function.identity()));
  }
  
  void popIn(final String room)
  {
    currentRoom = rooms.get(room);
  }
  
  String at()
  {
    return currentRoom.getLabel();
  }
  
  void walkTo(final String to)
  {
    currentRoom = Optional.ofNullable(rooms.get(to))
        .filter(currentRoom::walkTo)
        .orElseThrow(IllegalMoveException::new);
  }
}
