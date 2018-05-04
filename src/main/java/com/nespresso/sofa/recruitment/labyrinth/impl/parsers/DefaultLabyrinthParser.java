package com.nespresso.sofa.recruitment.labyrinth.impl.parsers;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nespresso.sofa.recruitment.labyrinth.impl.DefaultGate;
import com.nespresso.sofa.recruitment.labyrinth.impl.Gate;
import com.nespresso.sofa.recruitment.labyrinth.impl.NextWalkableRoom;
import com.nespresso.sofa.recruitment.labyrinth.impl.Room;
import com.nespresso.sofa.recruitment.labyrinth.impl.SensorGate;

public final class DefaultLabyrinthParser implements LabyrinthParser
{
  private static final String REGEX_SPLIT = "[$|]";

  private Map<String, Room> parseRawRooms(String[] cells)
  {
    return Arrays.stream(cells)
        .flatMap(cell -> Arrays.stream(cell.split(REGEX_SPLIT)))
        .collect(Collectors.toMap(Function.identity(), Room::new, (room1, room2) -> room1));
  }

  @Override
  public Map<String, Room> parseRooms(String[] cells)
  {
    final Map<String, Room> rooms = parseRawRooms(cells);

    for (final String cell : cells)
    {
      final String[] tokens = cell.split(REGEX_SPLIT);

      final Gate gate = cell.indexOf('|') != -1 ? new DefaultGate() : new SensorGate();

      final NextWalkableRoom rtl = new NextWalkableRoom(rooms.get(tokens[1]), gate);
      final NextWalkableRoom ltr = new NextWalkableRoom(rooms.get(tokens[0]), gate);

      rooms.get(tokens[0])
          .addNextWalkableRoom(rtl);
      rooms.get(tokens[1])
          .addNextWalkableRoom(ltr);
    }

    return rooms;
  }

}
