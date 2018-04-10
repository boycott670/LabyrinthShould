package com.nespresso.sofa.recruitment.labyrinth.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.nespresso.sofa.recruitment.labyrinth.IllegalMoveException;

public final class Room
{
  private final char label;
  private final Collection<Passage> nextPassages = new ArrayList<>();
  private final Collection<Passage> previousPassages = new ArrayList<>();
  
  public Room(char label)
  {
    this.label = label;
  }

  public char getLabel()
  {
    return label;
  }

  public Passage popIn ()
  {
    return new Passage(this);
  }
  
  public Passage walkTo (final Room targetRoom)
  {
    return Arrays.asList(nextPassages, previousPassages)
      .stream()
      .flatMap(Collection::stream)
      .filter(passage -> passage.getNextRoom().equals(targetRoom))
      .findFirst()
      .orElseThrow(IllegalMoveException::new)
      .checkGate();
  }

  public void addNextPassage (final Passage nextPassage)
  {
    nextPassages.add(nextPassage);
  }
  
  public void addPreviousPassage (final Passage previousPassage)
  {
    previousPassages.add(previousPassage);
  }
  
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + label;
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Room other = (Room) obj;
    if (label != other.label)
      return false;
    return true;
  }
}
