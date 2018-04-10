package com.nespresso.sofa.recruitment.labyrinth.entities;

import com.nespresso.sofa.recruitment.labyrinth.ClosedDoorException;

public final class Passage
{
  private final Gate gateToNextRoom;
  private final Room nextRoom;
  
  public Passage(Room nextRoom)
  {
    this(null, nextRoom);
  }

  public Passage(Gate gateToNextRoom, Room nextRoom)
  {
    this.gateToNextRoom = gateToNextRoom;
    this.nextRoom = nextRoom;
  }

  public Gate getGateToNextRoom()
  {
    return gateToNextRoom;
  }

  public Room getNextRoom()
  {
    return nextRoom;
  }
  
  public Passage checkGate ()
  {
    if (gateToNextRoom.isClosed())
    {
      throw new ClosedDoorException();
    }
    
    return this;
  }
}
