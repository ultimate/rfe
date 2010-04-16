package RacingForEngineers.Panels;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Cursor;
import java.awt.image.*;

import RacingForEngineers.*;
import RacingForEngineers.Panels.*;
import RacingForEngineers.ActionListeners.*;

public class GameFieldPanel extends JPanel implements RFEConstants//, ImageObserver
{
	public GameFieldPanel(RFEGUI rfegui)
  {
  	this.addMouseListener(new MousePositionListener(rfegui));
  	this.addMouseMotionListener(new MousePositionListener(rfegui));
  }

  public void paint(Graphics g)
  {
  	if(game.getStatus() != Game.INACTIVE)
    {
	    g.drawImage( game.getTrack().getTrackIcon().getImage(), 0, 0, null);
	    drawGrid(g);
      if(game.getStatus() != Game.NOTINITED)
      {
	      // so zeichnen, dass actualPlayer zuletzt
	      for(int i = game.getPlayerCounter().getActualPlayer()+1; i < game.getPlayerCounter().getActualPlayer()+game.getPlayerCounter().getNumberOfPlayers()+1; i++)
	      {
	        game.getPlayers()[i%game.getPlayerCounter().getNumberOfPlayers()].drawCar(g, true, game.getPlayers()[i%game.getPlayerCounter().getNumberOfPlayers()].getRouteLength());
	      }
      }
	      game.getPlayers()[game.getPlayerCounter().getActualPlayer()].drawPossibleMoves(g);
	      if( game.getPlayers()[game.getPlayerCounter().getActualPlayer()].getFocusedPoint() != null)
	        game.getPlayers()[game.getPlayerCounter().getActualPlayer()].markPoint(g,game.getPlayers()[game.getPlayerCounter().getActualPlayer()].getFocusedPoint(),game.getPlayers()[game.getPlayerCounter().getActualPlayer()].getPossibilityColor());
    }
    else
    	g.drawImage( (new ImageIcon("Data/Images/rfemain-blanco.png")).getImage(), 0, 0, null);
  }

  public void drawGrid(Graphics g)
  {
  	Color oldColor = g.getColor();
    g.setColor(game.getTrack().getGridColor());
    for( int h = 0; h <= this.getSize().getWidth(); h += game.getTrack().getGridSize())
    {
      g.drawLine(h,0,h,(int)this.getSize().getHeight()-2);
    }
    for( int v = 0; v <= this.getSize().getHeight(); v += game.getTrack().getGridSize())
    {
      g.drawLine(0,v,(int)this.getSize().getWidth()-2,v);
    }
    g.setColor(oldColor);
  }
}