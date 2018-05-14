package com.sqli.maze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import com.sqli.maze.exceptions.ClosedDoorException;

public final class Room
{
  private final String label;
  private final Collection<Door> adjacentDoors;
  
  public Room(String label)
  {
    this.label = label;
    adjacentDoors = new ArrayList<>();
  }
  
  public String getLabel()
  {
    return label;
  }

  public void addAdjacentDoor(final Door door)
  {
    adjacentDoors.add(door);
  }
  
  boolean walkTo(final Room to)
  {
    final Optional<Door> targetAdjacentDoor = adjacentDoors.stream()
        .filter(door -> door.walkTo(this, to))
        .findFirst();
    
    if (targetAdjacentDoor.isPresent())
    {
      if (targetAdjacentDoor.get().isClosed())
      {
        throw new ClosedDoorException();
      }
      
      return true;
    }
    
    return false;
  }
  
  void closeLastDoor(final Room to)
  {
    adjacentDoors.stream().forEach(door -> door.closeLastDoor(this, to));
  }

  @Override
  public boolean equals(Object other)
  {
    return other instanceof Room ? Objects.equals(label, ((Room)other).label) : false;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(label);
  }
}
