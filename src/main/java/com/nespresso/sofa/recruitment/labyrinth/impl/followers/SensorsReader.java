package com.nespresso.sofa.recruitment.labyrinth.impl.followers;

import java.util.ArrayList;
import java.util.Collection;

import com.nespresso.sofa.recruitment.labyrinth.impl.Gate;
import com.nespresso.sofa.recruitment.labyrinth.impl.Room;
import com.nespresso.sofa.recruitment.labyrinth.impl.SensorGate;

public final class SensorsReader implements WalkerFollower
{
  private Room previousRoom;
  private final Collection<String> pathSegments = new ArrayList<>();

  @Override
  public void poppedIn(Room startRoom)
  {
    previousRoom = startRoom;
  }

  @Override
  public void passedThroughGate(Gate gate, Room nextRoom)
  {
    if (gate instanceof SensorGate)
    {
      pathSegments.add(String.format("%s%s", previousRoom.getLabel(), nextRoom.getLabel()));
    }

    previousRoom = nextRoom;
  }

  @Override
  public String readWalk()
  {
    return String.join(";", pathSegments);
  }

}
