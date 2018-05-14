package com.sqli.maze;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sqli.maze.exceptions.IllegalMoveException;

public class MazeTest
{
  @Test
  public void be_Walkable_Till_The_End()
  {
    Maze mz = new Maze("A$B", "A$C", "C|E", "B$D", "B|E", "E$F", "D$F", "F|G");
    mz.popIn("A");
    mz.walkTo("B");
    mz.walkTo("E");
    assertEquals("E", mz.at());
    mz.walkTo("F");
    mz.walkTo("G");
    assertEquals("G", mz.at());
  }

  @Test(expected = IllegalMoveException.class)
  public void refuse_Illegal_Move()
  {
    Maze mz = new Maze("A$B", "A$C", "B$D");
    mz.popIn("A");
    mz.walkTo("B");
    mz.walkTo("E"); // rooms E does not exist in the mz
  }

  @Test(expected = IllegalMoveException.class)
  public void refuse_Move_Without_Path()
  {
    Maze mz = new Maze("A$B", "A$C", "B$D");
    mz.popIn("A");
    mz.walkTo("B");
    mz.walkTo("C"); // Can not reach C from B
  }

  @Test
  public void allow_Cyclic_Path()
  {
    Maze mz = new Maze("A$B", "A$C", "C|E", "B$D", "B|E", "E$F", "D$F", "F|G");
    mz.popIn("A");
    mz.walkTo("B");
    mz.walkTo("D");
    assertEquals("D", mz.at());
    mz.walkTo("F");
    mz.walkTo("E");
    assertEquals("E", mz.at());
    mz.walkTo("B");
    mz.walkTo("D");
    assertEquals("D", mz.at());
    mz.walkTo("F");
    mz.walkTo("G");
    assertEquals("G", mz.at());
  }

}
