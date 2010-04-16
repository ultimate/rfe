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

public class Game implements RFEConstants
{
  public static int INACTIVE 	= 0;
	public static int NOTINITED = 1;
  public static int ACTIVE 		= 2;
  public static int FINISHED 	= 3;

  private RFETrack track = new RFETrack();
  private int numberOfRounds = 1;
  private PlayerCounter p = new PlayerCounter();
  private Car[] c = null;
  private int status = INACTIVE;

  private Point[][] possibleStarts = null;
  private boolean[][] possibleValidStarts = null;

  public void startNewGame(RFETrack track, int numberOfRounds, int numberOfPlayers, Car[] c)
  {
  	this.track = track;
    this.numberOfRounds = numberOfRounds;
  	p.restart();
    p.setNumberOfPlayers(numberOfPlayers);
    p.setFirstPlayer( (int)( Math.random()*numberOfPlayers ) );
    this.c = c;

    status = NOTINITED;

    calcPossibleStarts();

    //c[p.getActualPlayer()].calcPossibleMoves();
  }

  public void	startGame()
  {
    for(int i = 0; i < c.length; i++)
    {
    	if(!c[i].hasStartPosition())
      	return;
    }
    status = ACTIVE;
  }

  public void calcPossibleStarts()
  {
  	possibleStarts = track.getPossibleStartPositions();
    possibleValidStarts = new boolean[possibleStarts.length][1];
    for(int i = 0; i < possibleValidStarts.length; i++)
    {
    	possibleValidStarts[i][0] = true;
    }
  }

  public void 					finishGame()			   			{ status = FINISHED; 		 				}
  public void 					quitGame()	   						{ status = INACTIVE;  					}
  public int						getStatus()								{	return status;								}
  public PlayerCounter 	getPlayerCounter() 				{ return p; 										}
  public Car[] 				 	getPlayers() 							{ return c; 										}
  public RFETrack 			getTrack()	 							{ return track; 								}
  public void		 				setTrack(RFETrack track)	{ this.track = track;			 			}
  public Point[][]			getPossibleStarts()	 			{ return possibleStarts;				}
  public boolean[][]		getPossibleValidStarts()	{ return possibleValidStarts;		}
}