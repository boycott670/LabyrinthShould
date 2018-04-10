package com.nespresso.sofa.recruitment.labyrinth.parsers;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.nespresso.sofa.recruitment.labyrinth.entities.Gate;
import com.nespresso.sofa.recruitment.labyrinth.entities.Passage;
import com.nespresso.sofa.recruitment.labyrinth.entities.Room;
import com.nespresso.sofa.recruitment.labyrinth.entities.SensorGate;

public final class DefaultLabyrinthSlotsParser implements LabyrinthSlotsParser
{
  @Override
  public Map<Character, Room> parseLabyrinthSlots(String... labyrinthSlots)
  {
    final Map<Character, Room> rooms = Arrays.stream(labyrinthSlots)
      .map(String::toCharArray)
      .flatMap(tokens -> Stream.of(tokens[0], tokens[2]))
      .map(Room::new)
      .collect(Collectors.toMap(
          Room::getLabel,
          Function.identity(),
          (room1, room2) -> room1));
    
    final Function<? super Character, ? extends Supplier<? extends Gate>> gateSupplier = gateCode -> gateCode == '$' ? SensorGate::new : Gate::new;
      
    for (final String labyrinthSlot : labyrinthSlots)
    {
      final char[] slotTokens = labyrinthSlot.toCharArray();
      
      final Gate gate = gateSupplier.apply(slotTokens[1]).get();
      
      final Passage nextPassage = new Passage(gate, rooms.get(slotTokens[2]));
      final Passage previousPassage = new Passage(gate, rooms.get(slotTokens[0]));
      
      rooms.get(slotTokens[0]).addNextPassage(nextPassage);
      rooms.get(slotTokens[2]).addPreviousPassage(previousPassage);
    }
    
    return rooms;
  }
}
