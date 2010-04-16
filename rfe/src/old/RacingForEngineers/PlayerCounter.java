package RacingForEngineers;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Cursor;

import RacingForEngineers.*;
import RacingForEngineers.Panels.*;
import RacingForEngineers.ActionListeners.*;

public class PlayerCounter implements RFEConstants
{
	private static final int maxPlayers = 8;
  private int numberOfPlayers = 0;
  private int actualPlayer = 0;
  private boolean firstMove = true;

	public PlayerCounter()
	{
    this.numberOfPlayers = maxPlayers;
  }

  public void setNumberOfPlayers(int numberOfPlayers)
  {
  	this.numberOfPlayers = numberOfPlayers;
    firstMove = true;
  }

  public int getNumberOfPlayers()
  {
    return numberOfPlayers;
  }

  public static int getMaxNumberOfPlayers()
  {
    return PlayerCounter.maxPlayers;
  }

  public void setFirstPlayer(int firstPlayer)
  {
  	if(firstMove)
    	this.actualPlayer = firstPlayer;
  }

	public void nextPlayer()
  {
    if(firstMove)
      firstMove = false;

  	int i = 0;
    do
    {
    	actualPlayer = (actualPlayer + 1)%numberOfPlayers;
      i++;
    }
    while( !game.getPlayers()[actualPlayer].hasPossibility() && i <= numberOfPlayers );
    // keiner mehr möglich
    if( i > numberOfPlayers )
    {
    	game.finishGame();
      //game.quitGame(); // später an anderer stelle
    }
  }

  public int getActualPlayer()
  {
  	return actualPlayer;
  }

  public void restart()
  {
    firstMove = true;
  }
}