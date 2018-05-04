package com.nespresso.sofa.recruitment.labyrinth;

import java.util.Map;

import com.nespresso.sofa.recruitment.labyrinth.impl.Room;
import com.nespresso.sofa.recruitment.labyrinth.impl.followers.SensorsReader;
import com.nespresso.sofa.recruitment.labyrinth.impl.followers.WalkerFollower;
import com.nespresso.sofa.recruitment.labyrinth.impl.parsers.DefaultLabyrinthParser;
import com.nespresso.sofa.recruitment.labyrinth.impl.parsers.LabyrinthParser;

final class Labyrinth
{
  private final LabyrinthParser labyrinthParser = new DefaultLabyrinthParser();
  private final WalkerFollower sensorsPathWalkerFollower = new SensorsReader();

  private final Map<String, Room> rooms;

  private Room currentRoom, previousCurrentRoom;

  Labyrinth(final String... cells)
  {
    rooms = labyrinthParser.parseRooms(cells);
  }

  void popIn(final String room)
  {
    currentRoom = rooms.get(room);
    sensorsPathWalkerFollower.poppedIn(currentRoom);
  }

  void walkTo(final String room)
  {
    final Room targetRoom = rooms.get(room);

    currentRoom.walkTo(targetRoom, sensorsPathWalkerFollower);

    previousCurrentRoom = currentRoom;
    currentRoom = targetRoom;
  }

  void closeLastDoor()
  {
    currentRoom.closeLastDoor(previousCurrentRoom);
  }

  String readSensors()
  {
    return sensorsPathWalkerFollower.readWalk();
  }
}
