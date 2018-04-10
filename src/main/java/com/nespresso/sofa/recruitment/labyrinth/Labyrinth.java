package com.nespresso.sofa.recruitment.labyrinth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.nespresso.sofa.recruitment.labyrinth.entities.Passage;
import com.nespresso.sofa.recruitment.labyrinth.entities.Room;
import com.nespresso.sofa.recruitment.labyrinth.parsers.DefaultLabyrinthSlotsParser;
import com.nespresso.sofa.recruitment.labyrinth.parsers.LabyrinthSlotsParser;

public final class Labyrinth
{
  private final LabyrinthSlotsParser parser = new DefaultLabyrinthSlotsParser();
  
  private final Map<? super Character, ? extends Room> rooms;
  
  private Passage currentPassage;
  
  private final List<Passage> tracedPassages = new ArrayList<>();
  
  public Labyrinth (final String... labyrinthSlots)
  {
    rooms = parser.parseLabyrinthSlots(labyrinthSlots);
  }
  
  private Room sanitizeUserRoomInput (final String room)
  {
    Function<? super String, ? extends Character> firstChar = string -> string.charAt(0);
    Predicate<? super String> notEmpty = string -> !string.isEmpty();
    
    return Optional.ofNullable(room)
      .filter(notEmpty)
      .map(firstChar)
      .map(rooms::get)
      .orElseThrow(IllegalMoveException::new);
  }
  
  public void popIn (final String room)
  {
    currentPassage = sanitizeUserRoomInput(room).popIn();
    tracedPassages.add(currentPassage);
  }
  
  public void walkTo (final String room)
  {
    currentPassage = currentPassage.getNextRoom().walkTo(sanitizeUserRoomInput(room));
    tracedPassages.add(currentPassage);
  }
  
  public void closeLastDoor ()
  {
    currentPassage.getGateToNextRoom().close();
  }
  
  public String readSensors ()
  {
    return IntStream.range(1, tracedPassages.size())
      .mapToObj(index -> tracedPassages.get(index).readPassage(tracedPassages.get(index - 1)))
      .filter(Objects::nonNull)
      .collect(Collectors.joining(";"));
  }
}
