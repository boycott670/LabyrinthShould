package com.nespresso.sofa.recruitment.labyrinth;

import org.junit.Test;

public class LabyrinthShould
{
  @Test
  public void be_Walkable_Till_The_End()
  {
    Labyrinth labyrinth = new Labyrinth("A$B", "A$C", "C|E", "B$D", "B|E", "E$F", "D$F", "F|G");
    labyrinth.popIn("A");
    labyrinth.walkTo("B");
    labyrinth.walkTo("E");
    labyrinth.walkTo("F");
    labyrinth.walkTo("G");
  }

  @Test(expected = IllegalMoveException.class)
  public void refuse_Illegal_Move()
  {
    Labyrinth labyrinth = new Labyrinth("A$B", "A$C", "B$D");
    labyrinth.popIn("A");
    labyrinth.walkTo("B");
    labyrinth.walkTo("E"); // room E does not exist in the labyrinth
  }

  @Test(expected = IllegalMoveException.class)
  public void refuse_Move_Without_Path()
  {
    Labyrinth labyrinth = new Labyrinth("A$B", "A$C", "B$D");
    labyrinth.popIn("A");
    labyrinth.walkTo("B");
    labyrinth.walkTo("C"); // Can not reach C from B
  }

  @Test
  public void allow_Cyclic_Path()
  {
    Labyrinth labyrinth = new Labyrinth("A$B", "A$C", "C|E", "B$D", "B|E", "E$F", "D$F", "F|G");
    labyrinth.popIn("A");
    labyrinth.walkTo("B");
    labyrinth.walkTo("D");
    labyrinth.walkTo("F");
    labyrinth.walkTo("E");
    labyrinth.walkTo("B");
    labyrinth.walkTo("D");
    labyrinth.walkTo("F");
    labyrinth.walkTo("G");
  }

  @Test
  public void allow_Back_And_Forth()
  {
    Labyrinth labyrinth = new Labyrinth("A$B", "A$C", "C|E", "B$D", "B|E", "E$F", "D$F", "F|G");
    labyrinth.popIn("A");
    labyrinth.walkTo("C");
    labyrinth.walkTo("A");
    labyrinth.walkTo("B");
    labyrinth.walkTo("D");
  }
}
