package com.nespresso.sofa.recruitment.labyrinth.impl.parsers;

import java.util.Map;

import com.nespresso.sofa.recruitment.labyrinth.impl.Room;

public interface LabyrinthParser
{
  Map<String, Room> parseRooms(final String[] cells);
}
