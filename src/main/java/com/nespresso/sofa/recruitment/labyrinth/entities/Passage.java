package com.nespresso.sofa.recruitment.labyrinth.entities;

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
}
