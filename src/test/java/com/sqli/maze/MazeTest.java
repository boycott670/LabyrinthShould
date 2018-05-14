package com.sqli.maze;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
