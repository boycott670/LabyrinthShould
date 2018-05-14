package com.sqli.maze.visitors.rooms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.sqli.maze.Door;
import com.sqli.maze.Room;

public final class SensorDoorRoomsVisitor implements RoomsVisitor
{
  private static final class PathEntry implements Entry<Room, Room>
  {
    private final Room leftRoom;
    private final Room rightRoom;
    
    private PathEntry(Room leftRoom, Room rightRoom)
    {
      this.leftRoom = leftRoom;
      this.rightRoom = rightRoom;
    }

    @Override
    public Room getKey()
    {
      return leftRoom;
    }

    @Override
    public Room getValue()
    {
      return rightRoom;
    }

    @Override
    public Room setValue(Room value)
    {
      throw new UnsupportedOperationException();
    }
  }
  
  private final Collection<Entry<Room, Room>> path = new ArrayList<>();
  
  private Room room;
  private Room previousRoom;

  @Override
  public void startIn(Room room)
  {
    previousRoom = room;
  }

  @Override
  public void walkTo(final Door door, Room room)
  {
    if (door.isSensor())
    {
      path.add(new PathEntry(previousRoom, room));
    }
    
    previousRoom = room;
  }

  @Override
  public String readPath()
  {
    return path.stream()
        .map(pathEntry -> String.format("%s%s", pathEntry.getKey()
            .getLabel(),
            pathEntry.getValue()
                .getLabel()))
        .collect(Collectors.joining(";"));
  }

}
