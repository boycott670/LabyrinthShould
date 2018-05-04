package com.nespresso.sofa.recruitment.labyrinth.impl;

import java.util.Objects;

import com.nespresso.sofa.recruitment.labyrinth.ClosedDoorException;
import com.nespresso.sofa.recruitment.labyrinth.impl.followers.WalkerFollower;

public final class NextWalkableRoom
{
  private final Room room;
  private final Gate gate;

  public NextWalkableRoom(Room room, Gate gate)
  {
    this.room = room;
    this.gate = gate;
  }

  public boolean canWalkTo(final Room targetRoom)
  {
    return Objects.equals(room, targetRoom);
  }

  public void walkToNextRoom(final WalkerFollower walkerFollower)
  {
    if (gate.isClosed())
    {
      throw new ClosedDoorException();
    }

    walkerFollower.passedThroughGate(gate, room);
  }

  public void closeDoor()
  {
    gate.close();
  }
}
