package com.sqli.maze.visitors.rooms;

import com.sqli.maze.Door;
import com.sqli.maze.Room;

public interface RoomsVisitor
{
  void startIn(final Room room);
  
  void walkTo(final Door door, final Room room);
  
  String readPath();
}
