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

public class Route implements RFEConstants
{
  private final double arrowAngle = Math.PI/6.0;
  private final double arrowLength = 0.6;

  private Point[] p = new Point[1];

	public Route(Point p)
  {
  	if( p != null )
    	this.p[0] = new Point(p);
    else
    	this.p = new Point[0];
  }

  public void addPoint(Point p)
  {
    Point[] helpP = new Point[this.p.length + 1];
    for (int i = 0 ; i < this.p.length ; i++)
    {
    	helpP[i] = this.p[i];
    }
    helpP[this.p.length] = new Point(p);
    this.p = helpP;
  }

  public Point getPoint(int index)
  {
    return p[index];
  }

  public void drawRoute(Graphics g, Car c)
  {
    for(int i=0; i<p.length-1;i++)
    {
    	if(!( (p[i].getX() == p[i+1].getX()) && (p[i].getY() == p[i+1].getY()) ))
      {
      	c.drawCar(g, false, i);
				// 5 fache Zeichnung damit dickere Linie
  	    g.drawLine(	(int) p[i].getX()	 ,	(int) p[i].getY()	 , (int) p[i+1].getX()	, (int) p[i+1].getY()	 );
    	  g.drawLine(	(int) p[i].getX()-1,	(int) p[i].getY()	 , (int) p[i+1].getX()-1, (int) p[i+1].getY()	 );
      	g.drawLine(	(int) p[i].getX()+1,	(int) p[i].getY()  , (int) p[i+1].getX()+1, (int) p[i+1].getY()  );
	      g.drawLine(	(int) p[i].getX()	 ,	(int) p[i].getY()-1, (int) p[i+1].getX()	, (int) p[i+1].getY()-1);
  	    g.drawLine(	(int) p[i].getX()	 ,	(int) p[i].getY()+1, (int) p[i+1].getX()	, (int) p[i+1].getY()+1);
    	  // Berechne Pfeil
	      double dx = p[i+1].getX() - p[i].getX();
		    double dy = p[i+1].getY() - p[i].getY();
    	  double alpha = 0;
	      if (dx == 0)
  	      alpha = Math.PI/2 * dy / Math.abs(dy);
    	  else if (dx < 0)
      	  alpha = Math.atan(dy/dx) + Math.PI;
	      else
  	    	alpha = Math.atan(dy/dx);
    	  Point p1 = new Point( (int)Math.round((p[i+1].getX() - Math.cos(alpha + arrowAngle/2)*arrowLength*game.getTrack().getGridSize())) ,
      												(int)Math.round((p[i+1].getY() - Math.sin(alpha + arrowAngle/2)*arrowLength*game.getTrack().getGridSize())) );
	      Point p2 = new Point( (int)Math.round((p[i+1].getX() - Math.cos(alpha - arrowAngle/2)*arrowLength*game.getTrack().getGridSize())) ,
  	    											(int)Math.round((p[i+1].getY() - Math.sin(alpha - arrowAngle/2)*arrowLength*game.getTrack().getGridSize())) );
    	  // Zeichne Pfeil 5 fach wegen dicke der Linie
      	// Teil1
	      g.drawLine( (int) p[i+1].getX()	 , (int) p[i+1].getY()	, (int) p1.getX()	 , (int) p1.getY()	);
  	    g.drawLine( (int) p[i+1].getX()-1, (int) p[i+1].getY()	, (int) p1.getX()-1, (int) p1.getY()	);
    	  g.drawLine( (int) p[i+1].getX()+1, (int) p[i+1].getY()	, (int) p1.getX()+1, (int) p1.getY()	);
      	g.drawLine( (int) p[i+1].getX()	 , (int) p[i+1].getY()-1, (int) p1.getX()	 , (int) p1.getY()-1);
	      g.drawLine( (int) p[i+1].getX()	 , (int) p[i+1].getY()+1, (int) p1.getX()	 , (int) p1.getY()+1);
  	    // Teil2
    	  g.drawLine( (int) p[i+1].getX()	 , (int) p[i+1].getY()	, (int) p2.getX()	 , (int) p2.getY()	);
      	g.drawLine( (int) p[i+1].getX()-1, (int) p[i+1].getY()	, (int) p2.getX()-1, (int) p2.getY()	);
	      g.drawLine( (int) p[i+1].getX()+1, (int) p[i+1].getY()	, (int) p2.getX()+1, (int) p2.getY()	);
  	    g.drawLine( (int) p[i+1].getX()	 , (int) p[i+1].getY()-1, (int) p2.getX()	 , (int) p2.getY()-1);
    	  g.drawLine( (int) p[i+1].getX()	 , (int) p[i+1].getY()+1, (int) p2.getX()	 , (int) p2.getY()+1);
      }
    }
  }

  public int getLength()
  {
		return p.length-1;
  }
}