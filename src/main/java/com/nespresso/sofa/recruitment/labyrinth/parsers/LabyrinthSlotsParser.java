package com.nespresso.sofa.recruitment.labyrinth.parsers;

import java.util.Map;

import com.nespresso.sofa.recruitment.labyrinth.entities.Room;

public interface LabyrinthSlotsParser
{
  Map<Character, Room> parseLabyrinthSlots (final String... labyrinthSlots);
}
