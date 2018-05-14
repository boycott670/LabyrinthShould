package com.sqli.maze.parsers;

import java.util.Set;

import com.sqli.maze.Room;

public interface MazeParser
{
  Set<Room> parseRooms(final String[] maze);
  
  Set<Room> parseDoors(final Set<Room> rooms, final String[] maze);
}
