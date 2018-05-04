package com.nespresso.sofa.recruitment.labyrinth.impl.followers;

import com.nespresso.sofa.recruitment.labyrinth.impl.Gate;
import com.nespresso.sofa.recruitment.labyrinth.impl.Room;

public interface WalkerFollower
{
  void poppedIn(final Room startRoom);

  void passedThroughGate(final Gate gate, final Room nextRoom);

  String readWalk();
}
