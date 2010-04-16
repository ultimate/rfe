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

public class Car implements RFEConstants
{
  public static final Color[] playerColors = { new Color(255,  0,  0),
  										                         new Color(128,  0,  0),
	                                             new Color(  0,255,  0),
	                                             new Color(  0,128,  0),
	                                             new Color(  0,  0,255),
	                                             new Color(  0,  0,128),
	                                             new Color(  0,255,255),
	                                             new Color(  0,128,128),
	                                             new Color(255,  0,255),
	                                             new Color(128,  0,128),
	                                             new Color(255,255,  0),
	                                             new Color(128,128,  0),
	                                             new Color(128,128,128),
	                                             new Color(  0,  0,  0) };  // 14 colors
	public static final int FILLED_SMALL = 1;
  public static final int FILLED_LARGE = 2;
  public static final int EMPTY_SMALL = -1;
  public static final int EMPTY_LARGE = -2;

  private Color carColor;
  private Color possColor;
  private Route r;
  private Point[][] possibleMoves = new Point[3][3];
  private boolean[][] possibleValidMoves = new boolean[3][3];
	private Point focusedPoint = null;
  private String name;
  private boolean hasPossibility = true;
  private boolean hasStartPosition = false;

  private int carSize = (int)game.getTrack().getGridSize()/2 + ((game.getTrack().getGridSize()/2)-1)%2;

  public Car(String name, Color c, Point p)
  {
  	this.name = name;
    carColor = c;
    possColor = new Color( (carColor.getRed()+255)/2,
                           (carColor.getGreen()+255)/2,
                           (carColor.getBlue()+255)/2);
    r = new Route(p);
  	if(p != null)
    	calcPossibleMoves();
  }

  public Car(String name, int colorIndex, Point p)
  {
  	this.name = name;
    carColor = playerColors[colorIndex];
    possColor = new Color( (carColor.getRed()+255)/2,
                           (carColor.getGreen()+255)/2,
                           (carColor.getBlue()+255)/2);
    r = new Route(p);
  	if(p != null)
    	calcPossibleMoves();
  }

  public void  addPoint(Point p)
	{
  	r.addPoint(p);
    if( !hasStartPosition() )
    {
      hasStartPosition = true;
      for(int i = 0; i < possibleMoves.length; i++)
      {
      	if(possibleMoves[i][0].equals(p))
        	possibleValidMoves[i][0] = false;
      }
  		possibleMoves = new Point[3][3];
  		possibleValidMoves = new boolean[3][3];
		}
  	//calcPossibleMoves();
	}

  public int 			getRouteLength()  			{   return r.getLength();  	}
  public Color 		getCarColor()  					{  	return carColor;  			}
  public Color 		getPossibilityColor()  	{   return possColor;  			}
  public String 	getPlayerName()  				{   return name;  					}
  public void 		setCarSize(int carSize) {   this.carSize = carSize; }
  public int 			getCarSize()  					{   return carSize;  				}
  public boolean 	hasPossibility()  			{  	return hasPossibility;  }
  public Point 		getFocusedPoint()  			{   return focusedPoint;  	}

  public void setFocusedPoint(Point p)
  {
    if(p!=null)
      focusedPoint = new Point(p);
    else
      focusedPoint = null;
  }

  public void drawCar(Graphics g, boolean drawRoute, int index)
  {
    Color oldColor = g.getColor();
    g.setColor(carColor);
    if(drawRoute)
    	r.drawRoute(g, this);
    Point position = r.getPoint(index);
    g.fillOval( (int) ( position.getX() - (carSize/2) ), (int) ( position.getY() - (carSize/2) ), carSize, carSize);
    g.setColor(oldColor);
  }

  public void drawPoint(Graphics g, Point p, Color c, int type)
  {
    Color oldColor = g.getColor();
    g.setColor(c);
    switch (type)
    {
    	case FILLED_SMALL:
				g.fillOval( (int) ( p.getX() - (carSize/2)     ), (int) ( p.getY() - (carSize/2)     ), carSize, carSize);
        break;
      case EMPTY_SMALL:
				g.drawOval( (int) ( p.getX() - (carSize/2)     ), (int) ( p.getY() - (carSize/2)     ), carSize-1, carSize-1);
        break;
    	case FILLED_LARGE:
				g.fillOval( (int) ( p.getX() - (carSize/2) - 2 ), (int) ( p.getY() - (carSize/2) - 2 ), carSize+3, carSize+3);
        break;
      case EMPTY_LARGE:
				g.drawOval( (int) ( p.getX() - (carSize/2) - 2 ), (int) ( p.getY() - (carSize/2) - 2 ), carSize+3, carSize+3);
        break;
    }
    g.setColor(oldColor);
	}

  public void markPoint(Graphics g, Point p, Color c)
  {
  	drawPoint(g,p,c,EMPTY_LARGE);
  }

  public void unmarkPoint(Graphics g, Point p, Color c)
  {
		markPoint(g,p,bgColor); // hier haben wir ein problem! malt mit hintergrundfarbe, statt dem was tatsächlich im hintergrund ist!
    Color oldColor = g.getColor();
    g.setColor(game.getTrack().getGridColor());
    g.drawLine( (int) ( p.getX() - carSize ), (int) ( p.getY() 					 ),
								(int) ( p.getX() + carSize ), (int) ( p.getY() 					 ) );
    g.drawLine( (int) ( p.getX()  				 ), (int) ( p.getY() - carSize ),
								(int) ( p.getX()  				 ), (int) ( p.getY() + carSize ) );
    g.setColor(oldColor);
    drawPoint(g,p,c,FILLED_SMALL);
  }

  public void calcPossibleMoves()
  {
  	if( hasStartPosition )
    {
	    if(!hasPossibility)
	      return;
	    else
	      hasPossibility = false;

	    Point center;
	    Point last;
	    if (r.getLength() == 0)
	    {
	      center = r.getPoint(0);
	      last = center;
	    }
	    else
	    {
	      //abfrage der einflussgrößen oil, sand, etc.
	      last = r.getPoint( r.getLength() );
	      Point prelast = r.getPoint( r.getLength()-1);
	      center =  new Point( (int)(2*last.getX() - prelast.getX()) ,
	                           (int)(2*last.getY() - prelast.getY())  );
	    }
	    for(int x = 0; x < possibleMoves.length; x++)
	    {
	      for(int y = 0; y < possibleMoves[x].length; y++)
	      {
	        possibleMoves[x][y] = new Point ( (int)( center.getX() + (x - 1)*game.getTrack().getGridSize() ) ,
	                                          (int)( center.getY() + (y - 1)*game.getTrack().getGridSize() ) );

	        if( ( ( x==1 ) && ( y==1 ) ) || ( possibleMoves[x][y].getX() < 0 ) || ( possibleMoves[x][y].getY() < 0 ) || ( possibleMoves[x][y].getX() >= game.getTrack().getTrackSize().getWidth() ) || ( possibleMoves[x][y].getY() >= game.getTrack().getTrackSize().getHeight() ) )
	          possibleValidMoves[x][y] = false;
	        else
	        {
	          possibleValidMoves[x][y] = game.getTrack().isValidMove(last,possibleMoves[x][y]);
	          if(possibleValidMoves[x][y])
	            hasPossibility = true;
	        }
	      }
	    }
    }
    else
    {
      possibleMoves 			= game.getPossibleStarts();
      possibleValidMoves 	= game.getPossibleValidStarts();
    }
  }

  public void drawPossibleMoves(Graphics g)
  {
    for(int x = 0; x < possibleMoves.length; x++)
    {
      for(int y = 0; y < possibleMoves[x].length; y++)
      {
        if( !possibleValidMoves[x][y] )
          drawPoint(g,possibleMoves[x][y],possColor,EMPTY_SMALL);
        else
          drawPoint(g,possibleMoves[x][y],possColor,FILLED_SMALL);
      }
    }
	}

  public Point getPossiblePoint(double px, double py) throws Exception
  {
    for(int x = 0; x < possibleMoves.length; x++)
    {
      for(int y = 0; y < possibleMoves[x].length; y++)
      {
        if( ( possibleValidMoves[x][y] ) && (possibleMoves[x][y].distance(px,py) < (double)game.getTrack().getGridSize()/3.0/*1.0/3.0*/) )
        {
          return possibleMoves[x][y];
        }
      }
    }
    throw new Exception();
  }


  // ??? notwendig ???
  // random unten, wenn color index = -1   !!!
  public static Car[] randomInitCars(int numberOfCars) throws IllegalArgumentException
  {
    if(numberOfCars > playerColors.length)
    	throw new IllegalArgumentException("Number of cars must be lower or equal 14!");
  	Car c[] = new Car[numberOfCars];
    int colorIndex[] = new int[numberOfCars];
    for(int i = 0; i < numberOfCars; i++)
    {
      boolean twice = true;
      do
      {
	      colorIndex[i] = (int)(Math.random()*14);
        twice = false;
        for(int j = 0; j < i; j++)
        {
        	if( colorIndex[j] == colorIndex[i] )
          {
          	twice = true;
            break;
          }
        }
      }
      while( twice );

      c[i] = new Car("Player " + i, colorIndex[i], null);
    }
    return c;
  }


  public static Car[] initCars(int numberOfCars, String[] names, int[] colorIndex) throws IllegalArgumentException
  {
  	// fehler abfangen
  	if(names.length < numberOfCars)
      throw new IllegalArgumentException("Illegal number of player-names!");
    if(colorIndex.length < numberOfCars)
      throw new IllegalArgumentException("Illegal number of colors!");
    if(numberOfCars > playerColors.length)
    	throw new IllegalArgumentException("Number of cars must be lower or equal 14!");
    // leere spieler init
    Car c[] = new Car[numberOfCars];
    // check chosen colors
    boolean chosenColors[] = new boolean[playerColors.length];
    for(int i = 0; i < chosenColors.length; i++)
    {
    	chosenColors[i] = false;
    }
    for(int i = 0; i < numberOfCars; i++)
    {
    	if( (colorIndex[i] >= 0) && (colorIndex[i] < chosenColors.length) )
      {
      	if( chosenColors[ colorIndex[i] ] == true )
      		throw new IllegalArgumentException("Color has been chosen twice");
        chosenColors[ colorIndex[i] ] = true;
      }
      else if( colorIndex[i] != -1 )
      	throw new IllegalArgumentException("Illegal color index: " + colorIndex[i] );
    }
    for(int i = 0; i < numberOfCars; i++)
    {
			// falls keine farbe colorIndex neu mit Zufallsfarbe die noch nicht vergeben
    	if( colorIndex[i] == -1)
      {
      	int r;
        do
        {
        	r = (int)(Math.random()*playerColors.length);
        }
      	while(chosenColors[r] == true);
        colorIndex[i] = r;
        chosenColors[r] = true;
      }
    	c[i] = new Car(names[i], colorIndex[i], null);
      System.out.println("N: " + names[i] + " ,C: " + colorIndex[i]);
    }
    return c;
  }

  public boolean hasStartPosition()
  {
  	return hasStartPosition;
  }
}