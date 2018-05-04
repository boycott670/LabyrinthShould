package com.nespresso.sofa.recruitment.labyrinth.impl;

import java.util.ArrayList;
import java.util.Collection;

import com.nespresso.sofa.recruitment.labyrinth.IllegalMoveException;
import com.nespresso.sofa.recruitment.labyrinth.impl.followers.WalkerFollower;

public final class Room
{
  private final String label;
  private final Collection<NextWalkableRoom> nextWalkableRooms;

  public Room(String label)
  {
    this.label = label;
    nextWalkableRooms = new ArrayList<>();
  }

  public final String getLabel()
  {
    return label;
  }

  public void addNextWalkableRoom(final NextWalkableRoom nextWalkableRoom)
  {
    nextWalkableRooms.add(nextWalkableRoom);
  }

  public void walkTo(final Room targetRoom, final WalkerFollower walkerFollower)
  {
    nextWalkableRooms.stream()
        .filter(nextWalkableRoom -> nextWalkableRoom.canWalkTo(targetRoom))
        .findAny()
        .orElseThrow(IllegalMoveException::new)
        .walkToNextRoom(walkerFollower);
  }

  public void closeLastDoor(final Room adjacentRoom)
  {
    nextWalkableRooms.stream()
        .filter(nextWalkableRoom -> nextWalkableRoom.canWalkTo(adjacentRoom))
        .findAny()
        .ifPresent(NextWalkableRoom::closeDoor);
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((label == null) ? 0 : label.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Room other = (Room) obj;
    if (label == null)
    {
      if (other.label != null)
        return false;
    } else if (!label.equals(other.label))
      return false;
    return true;
  }
}
