package com.nespresso.sofa.recruitment.labyrinth.entities;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SensorGate extends Gate
{
  @Override
  public String read(Passage passage1, Passage passage2)
  {
    return Stream.of(passage1, passage2)
      .map(Passage::getNextRoom)
      .map(Room::getLabel)
      .map(String::valueOf)
      .collect(Collectors.joining());
  }
}
