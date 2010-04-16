package RacingForEngineers.ActionListeners;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Cursor;
import javax.swing.event.*;

import RacingForEngineers.*;
import RacingForEngineers.Panels.*;
import RacingForEngineers.ActionListeners.*;

public class MousePositionListener implements MouseInputListener, RFEConstants
{
  private RFEGUI rfegui;

  public MousePositionListener(RFEGUI rfegui)
  {
    this.rfegui = rfegui;
  }

  public void mouseClicked(MouseEvent e)
  {
    try
    {
    	game.getPlayers()[game.getPlayerCounter().getActualPlayer()].addPoint(game.getPlayers()[game.getPlayerCounter().getActualPlayer()].getFocusedPoint());
      game.getPlayers()[game.getPlayerCounter().getActualPlayer()].setFocusedPoint(null);
			//game.getPlayers()[game.getPlayerCounter().getActualPlayer()].calcPossibleMoves();
      game.getPlayerCounter().nextPlayer();
      game.getPlayers()[game.getPlayerCounter().getActualPlayer()].calcPossibleMoves();
      rfegui.getGameFieldPanel().repaint();
    }
    catch(Exception ex)
    {
    }
  }

  public void mouseMoved(MouseEvent e)
  {
    rfegui.getStatusPanel().setMousePosition(calcPointToCoordinate(e.getX()),calcPointToCoordinate(e.getY()));
  	if(game.getStatus() != Game.INACTIVE)
    {
	    try
	    {
	      Point possibleP = game.getPlayers()[game.getPlayerCounter().getActualPlayer()].getPossiblePoint((int)e.getX(),(int)e.getY());
	      if(possibleP != game.getPlayers()[game.getPlayerCounter().getActualPlayer()].getFocusedPoint())
	      {
	        game.getPlayers()[game.getPlayerCounter().getActualPlayer()].setFocusedPoint(possibleP);
	      }
	    }
	    catch(Exception ex)
	    {
	      game.getPlayers()[game.getPlayerCounter().getActualPlayer()].setFocusedPoint(null);
	    }
	    rfegui.getGameFieldPanel().repaint();    // für korrektes Zeichnen
    }
  }

  public void mouseDragged(MouseEvent e)  {    mouseMoved(e);  }
	public void mousePressed(MouseEvent e)  {  }
  public void mouseExited(MouseEvent e)  {  }
  public void mouseEntered(MouseEvent e)  {  }
  public void mouseReleased(MouseEvent e)  {  }

  public double calcPointToCoordinate(int XOrY)
  {
  	double dXOrY = Math.round(XOrY * 100.0 / game.getTrack().getGridSize())/100.0;
    return dXOrY;
  }
}