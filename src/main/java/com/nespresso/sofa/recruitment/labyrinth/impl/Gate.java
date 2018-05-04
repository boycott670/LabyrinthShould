package com.nespresso.sofa.recruitment.labyrinth.impl;

import com.nespresso.sofa.recruitment.labyrinth.DoorAlreadyClosedException;

public abstract class Gate
{
  private boolean isClosed = false;

  public final boolean isClosed()
  {
    return isClosed;
  }

  public final void close()
  {
    if (isClosed())
    {
      throw new DoorAlreadyClosedException();
    }

    isClosed = true;
  }
}
