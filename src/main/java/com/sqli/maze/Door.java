package com.sqli.maze;

import java.util.Arrays;
import java.util.Objects;

public final class Door
{
  private final Room leftAdjacentRoom;
  private final Room rightAdjacentRoom;
  private final boolean isSensor;
  
  private boolean isClosed = false;
  
  public Door(Room leftAdjacentRoom, Room rightAdjacentRoom, boolean isSensor)
  {
    this.leftAdjacentRoom = leftAdjacentRoom;
    this.rightAdjacentRoom = rightAdjacentRoom;
    this.isSensor = isSensor;
  }
  
  boolean walkTo(final Room from, final Room to)
  {
    return Objects.equals(leftAdjacentRoom, from) ? Objects.equals(rightAdjacentRoom, to) : Objects.equals(leftAdjacentRoom, to);
  }
  
  void closeLastDoor(final Room from, final Room to)
  {
    if (Arrays.asList(leftAdjacentRoom, rightAdjacentRoom).containsAll(Arrays.asList(from, to)))
    {
      isClosed = true;
    }
  }
}
