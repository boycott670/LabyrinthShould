package com.sqli.maze.visitors.rooms;

import com.sqli.maze.Room;

public interface RoomsVisitor
{
  void startIn(final Room room);
  
  void walkTo(final Room room);
  
  String readPath();
}
