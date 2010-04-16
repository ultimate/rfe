package RacingForEngineers;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Cursor;

import RacingForEngineers.*;
import RacingForEngineers.Panels.*;
import RacingForEngineers.ActionListeners.*;

import Storage.*;

public class RFETrack implements RFEConstants
{
	// final static
  public static final Terrain[] terrain = {	new Terrain(0  , "------", "Terrain"			),
	                                          new Terrain(1  , "000000", "Border"				),
                                            new Terrain(2.1, "00FF00", "Start ccw"		),
                                            new Terrain(2.2, "00FF00", "Start cw"			),
	                                          new Terrain(3  , "FF0000", "Finish" 		  ),
                                            new Terrain(4  , "FFFF00", "Round"				),
	                                          new Terrain(5  , "000080", "Check"    		),
	                                          new Terrain(6  , "464600", "Oil"      		),
	                                          new Terrain(7  , "C8C864", "Sand"     		),
	                                          new Terrain(8  , "FF0800", "Bump"     		),
	                                          new Terrain(9  , "FF00FF", "Speed"    		) };
  public static final int timeFactor = 4;
  public static final int gridSizeFactor = 5;
  public static final int gridTransFactor = 5;
  public static final int maxRounds = 16;
  public static final int maxChecks = 8;
  public static final int maxTime = (32-1)*timeFactor;
  public static final int maxGridSize = (16)*gridSizeFactor;
  public static final int maxGridTrans = 100;
  public static final int maxTolerance = 127;
  public static final int maxMessageLength = 1023;
  private static final Dimension trackSize = new Dimension(1001,801);

  // strecke
  private ImageIcon trackIcon;
	private double[][] field = new double[(int)trackSize.getWidth()][(int)trackSize.getHeight()];

  // daten aus status pixel
  private int tolerance, rounds, direction, checks, players, time, gridSize, gridTrans, mode;
  private String message = "";
  private Color gridColor;

  public RFETrack()
  {
  	initValues();
  }

  public RFETrack(String imageSource) throws Exception
  {
  	initValues();
  	setTrack(imageSource);
  }

  public void initValues()
  {
  	this.tolerance = 0;
    this.rounds = 1;
    this.direction = 1;
    this.checks = 1;
    this.time = 0;
    this.gridSize = 20;
    this.gridTrans = 0;
    this.gridColor = new Color(150,150,150, (int)( (100-gridTrans)*255.0/100.0 ) );
    this.message = "";
  }

  private double[][] getFieldArray(ImageIcon trackIcon) throws Exception
  {
    Image i = trackIcon.getImage();
    int w = i.getWidth(null);
    int h = i.getHeight(null);
    PixelGrabber pg = new PixelGrabber(i, 0, 0, w, h, true);
    pg.grabPixels();
    int[] pixels = (int[])pg.getPixels();
    double[][] returnArray = new double[w][h];
		// STATUSABFRAGE DER ERSTEN ZWEI PIXELS --> BLACK IF NO CONFIG
    String status = Integer.toBinaryString(pixels[0]).substring(8,32) + Integer.toBinaryString(pixels[1]).substring(8,32);
    if( !status.equals("000000000000000000000000000000000000000000000000") )
    {
	    // --> CUSTOM COLORS (1 bit)
	      boolean custCol = (status.charAt(0) == '1');
      // --> TRACK-MODE (1 bit) -- Rundkurs(0), A-B(1)
      	this.mode = Integer.parseInt(status.substring(1,2),2);
      // INFO FOR MODE (2 bit)
      	if( mode == 0) //rundkurs
        {
      	// --> RICHTUNG 	(1 bit)
        	// mathematisch positiv = 1 <--> uhrzeigersinn = -1
        	if( status.charAt(2) == '0' )
        		this.direction = -1;
      	// --> RUNDEN   (4 bit)
        	this.rounds = Integer.parseInt(status.substring(3,7),2)+1;
        }
        else //A-B
        {
          // vorwärts = 1 <--> rückwärts = -1
        	if( status.charAt(2) == '0' )
        		this.direction = -1;
        }
    	// --> ANZAHL CHECKPOINTS		(3 bit)
      	this.checks = Integer.parseInt(status.substring(7,10),2)+1;
	    // --> RECOMMEND NUMBERS OF PLAYERS   (3 bit)
        this.players = Integer.parseInt(status.substring(10,13),2)+1;
	    // --> RECOMMEND MAXIMUM TIME  (5 bit)
      	this.time = Integer.parseInt(status.substring(13,18),2)*timeFactor;
      // --> CUSTOM GRID (1 bit)
        boolean custGrid = (status.charAt(18) == '1');
        if(custGrid)
        {
      // --> RECOMMEND GRIDSIZE (4 bit)
      			this.gridSize = Integer.parseInt(status.substring(19,23),2)*gridSizeFactor+gridSizeFactor;
      		// --> CUSTOM GRIDCOLOR FROM GRIDPIXEL
          	String gcs = Integer.toBinaryString(pixels[(terrain.length-1)*w]);
            int r = Integer.parseInt(gcs.substring( 8,16),2);
            int g = Integer.parseInt(gcs.substring(16,24),2);
            int b = Integer.parseInt(gcs.substring(24,32),2);
            this.gridColor = new Color(r,g,b);
		  // --> GRID TRANSPARENCY (7 bit)
          	this.gridTrans = Integer.parseInt(status.substring(23,28),2)*gridTransFactor;
            if(gridTrans > 100)
            	gridTrans = 100;
        }
      // --> INCLUDED MESSAGE (1 bit)
        boolean mess = (status.charAt(28) == '1');
        if( mess )
        {
      // --> CHECKSUM (6 bit)
        	String messCheck = status.substring(29,35);
      // --> ANZAHLL DER PIXEL FUER ZEICHEN : n (10 bit)
          int charPixels = Integer.parseInt( status.substring(35,45), 2);
          // --> CHECKSUM PRUEFEN
          if( Integer.toHexString(pixels[charPixels+2]).substring(2,8).equals(messCheck) )
          {
            // --> n PIXEL LESEN = 3*n ZEICHEN
            for(int p = 2; p < charPixels+2; p++)
            {
            	for(int j = 1; j < 4; j++)
              {
                this.message += (char)Integer.parseInt( Integer.toBinaryString(pixels[p]).substring(8*j,8*(j+1)), 2);
              }
            }
          }
      	}
      // --> ERST JETZT FARBEN LESEN, DA INFOS BENÖTIGT!!!
	      boolean colCheck = ( Integer.toHexString(pixels[(terrain.length)*w]).substring(2,6).toUpperCase().equals("C010") );
	      // farben definieren
	      if( custCol && colCheck ) // bedingung für selbstdef farben
	      {
	        for(int j = 1; j < terrain.length; j++)
	        {
	          String hexColor = Integer.toHexString(pixels[0+j*w]).substring(2,8).toUpperCase();
            System.out.println(hexColor);
	          terrain[j].setColorString(hexColor);
            if( j == 2 )
            	j++;
	        }
          this.tolerance = Integer.parseInt( Integer.toBinaryString(pixels[(terrain.length)*w]).substring(24,31), 2);
          if( !checkColors() )
          {
          	System.out.println("Invalid Colors  - Standard Colors Used");
            resetTerrains();
          }
	      }
        else
        	resetTerrains();
      // JETZT NOCH VERSCHIEDENE STARTS UND CHECKPOINTS
	      if( tolerance == 0 )
	      {
	        int diff = (direction - 1)/2;
	        int r = terrain[2].getRGB()[0]+diff;
	        int g = terrain[2].getRGB()[1]+diff;
	        int b = terrain[2].getRGB()[2]+diff;
	        String rgbHex = Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
	        terrain[2-diff].setColorString(rgbHex.toUpperCase());
	      }
      // --> COLORS ENDE
    	printParameters();
		}
    for(int x = 0; x < w; x++)
    {
    	for(int y = 0; y < h; y++)
      {
	      String fieldColor = Integer.toHexString(pixels[x+y*w]).substring(2,8); //.toUpperCase();
        returnArray[x][y] = terrain[0].getIndex(); // init with terrain
        // check other terrains

        //System.out.print("\n" + x + " , " + y + " : " );

        for(int j = 1; j < terrain.length; j++)
        {
        	// tolerance

          //System.out.print( terrain[j].getDistance(fieldColor) + " " );

	        if( terrain[j].getDistance(fieldColor) <= tolerance )
          {
          	//System.out.println(x + " , " + y);
	          returnArray[x][y] = terrain[j].getIndex();
            break;
          }
        }
      }
    }
    return returnArray;
  }

  public boolean isValidMove(Point p1, Point p2)
  {
    return !moveContainsTerrain(p1,p2,terrain[1]);
  }

  public boolean moveContainsTerrain(Point p1, Point p2, Terrain terrainToTest)
  {
   	if( ( field[(int)p1.getX()][(int)p1.getY()] == terrainToTest.getIndex() ) || ( field[(int)p2.getX()][(int)p2.getY()] == terrainToTest.getIndex() ) )
    	return true;
  	int dx = (int)(p2.getX() - p1.getX());
  	if( dx < 0 )
    	return moveContainsTerrain(p2,p1,terrainToTest);
    int dy = (int)(p2.getY() - p1.getY());
    if( dx == 0 )
    {
    	while( dy < 0 )
      {
        if( field[(int)p1.getX()][(int)p1.getY()+dy] == terrainToTest.getIndex() )
          return true;
        dy++;
      }
      while( dy > 0)
      {
        if( field[(int)p1.getX()][(int)p1.getY()+dy] == terrainToTest.getIndex() )
          return true;
        dy--;
      }
    }
    else if( dy == 0 )
    {
    	while( dx < 0 )
      {
        if( field[(int)p1.getX()+dx][(int)p1.getY()] == terrainToTest.getIndex() )
          return true;
        dx++;
      }
      while( dx > 0)
      {
        if( field[(int)p1.getX()+dx][(int)p1.getY()] == terrainToTest.getIndex() )
          return true;
        dx--;
      }
    }
    else if( dx == 1 && Math.abs(dy) == 1 )
    {
    	return false;
    }
    else if( (double)dy/(double)dx > 0 )
    {
    	double m 		= 	(double)	dy	/	(double)	dx;
      double my 	= 	(double)(dy-2)/	(double)	dx;
      double mx 	= 	(double)	dy	/	(double)(dx-2);
      double mxy 	= 	(double)(dy-2)/	(double)(dx-2);

      double a 		= Math.atan(m);
      double ay 	= Math.atan(my);
      double ax 	= Math.atan(mx);
      double axy 	= Math.atan(mxy);

      if(	Math.min( Math.min( Math.abs(a-ay), Math.abs(a-ax) ), Math.abs(a-axy) ) ==  Math.abs(a-ay))
      	return moveContainsTerrain( new Point((int)p1.getX()	, (int)p1.getY()+1), new Point((int)p2.getX()	 , (int)p2.getY()-1) , terrainToTest);
      else if (	Math.min( Math.min( Math.abs(a-ay), Math.abs(a-ax) ), Math.abs(a-axy) ) ==  Math.abs(a-ax) )
        return moveContainsTerrain( new Point((int)p1.getX()+1, (int)p1.getY()	), new Point((int)p2.getX()-1, (int)p2.getY()  ) , terrainToTest);
      else
      	return moveContainsTerrain( new Point((int)p1.getX()+1, (int)p1.getY()+1), new Point((int)p2.getX()-1, (int)p2.getY()-1) , terrainToTest);
    }
    else
    {
    	double m 		= 	(double)	dy	/	(double)	dx;
      double my 	= 	(double)(dy-2)/	(double)	dx;
      double mx 	= 	(double)	dy	/	(double)(dx-2);
      double mxy 	= 	(double)(dy-2)/	(double)(dx-2);

      double a 		= Math.atan(m);
      double ay 	= Math.atan(my);
      double ax 	= Math.atan(mx);
      double axy 	= Math.atan(mxy);

      if(	Math.min( Math.min( Math.abs(a-ay), Math.abs(a-ax) ), Math.abs(a-axy) ) ==  Math.abs(a-ay))
      	return moveContainsTerrain( new Point((int)p1.getX()	, (int)p1.getY()-1), new Point((int)p2.getX()	 , (int)p2.getY()+1) , terrainToTest);
      else if (	Math.min( Math.min( Math.abs(a-ay), Math.abs(a-ax) ), Math.abs(a-axy) ) ==  Math.abs(a-ax) )
        return moveContainsTerrain( new Point((int)p1.getX()+1, (int)p1.getY()	), new Point((int)p2.getX()-1, (int)p2.getY()  ) , terrainToTest);
      else
      	return moveContainsTerrain( new Point((int)p1.getX()+1, (int)p1.getY()-1), new Point((int)p2.getX()-1, (int)p2.getY()+1) , terrainToTest);
    }
    return false;
  }


  public ImageIcon getTrackIcon()
  {
    return trackIcon;
  }

  public void setTrack(String imageSource) throws Exception
  {
  	trackIcon = new ImageIcon(imageSource);
    double[][] tempArray = getFieldArray(trackIcon);
    if( ( tempArray.length != trackSize.getWidth() ) || ( tempArray[0].length != trackSize.getHeight() ) )
    {
    	for(int x = 0; x < trackSize.getWidth(); x++)
      {
      	for(int y = 0; y < trackSize.getHeight(); y++)
        {
          try
          {
          	field[x][y] = tempArray[x][y];
          }
          catch(ArrayIndexOutOfBoundsException e)
          {
           	field[x][y] = terrain[0].getIndex();
					}
        }
      }
    }
    else
    	field = tempArray;
  }

  public static RFETrack getRandomTrack()
  {
  	int trackIndex = (int)( Math.random() * (defTracks.length-1) ) + 1;
    String trackName = trackDir + defTracks[trackIndex];
    try
    {
    	return new RFETrack(trackName);
    }
    catch(Exception e)
    {
    	return null;
    }
  }

  public void printParameters()
  {
    System.out.println("Tolerance: " + tolerance );
    System.out.println("Rounds:    " + rounds    );
    if( direction > 0 )
    	System.out.println("Direction: counter-clockwise");
    else
    	System.out.println("Direction: clockwise");
    System.out.println("Checks:    " + checks		 );
    System.out.println("Time:      " + time			 );
    System.out.println("GridSize:  " + gridSize  );
    System.out.println("Message: \n" + message + "\n"  );

    for(int i = 0; i < terrain.length; i++)
    {
    	System.out.println(terrain[i].getColorString());
    }
  }

  public void resetTerrains()
  {
  	for(int i = 0; i < terrain.length; i++)
    {
    	terrain[i].reset();
    }
  }

  public boolean checkColors()
  {
  	for(int i = 0; i < terrain.length; i++)
    {
    	if( i == 2 )
        i++;
    	for(int j = i+1; j < terrain.length; j++)
    	{
      	if( terrain[i].getDistance( terrain[j].getColorString() ) <= tolerance )
        	return false;
      }
    }
    return true;
  }

  public Point[][] getPossibleStartPositions()
  {
		SimpleList<Point> possStartsList = new SimpleList<Point>();
    Point[][] possStarts;
    int diff = 0;

    if( tolerance == 0 )
    {
      diff = (direction - 1)/2;
    }
    for(int x = 0; x < field.length; x = x+gridSize)
    {
      for(int y = 0; y < field[0].length; y = y+gridSize)
      {
        String fieldColor = terrain[0].getColorString();
        for(int i = 1; i < terrain.length; i++)
        {
        	if( terrain[i].getIndex() == field[x][y] )
          {
            fieldColor = terrain[i].getColorString();
            break;
          }
        }
        if( ( terrain[2-diff].getDistance(fieldColor) <= tolerance )  && ( x % gridSize == 0 ) && ( y % gridSize == 0) )
      	{
          possStartsList.insertAfter( new Point(x,y) );
        }
      }
    }
    if( !possStartsList.isEmpty() )
    {
      possStartsList.reset();
      int l = 0;
      while( !possStartsList.isAtEnd() )
      {
        l++;
        possStartsList.increment();
      }
      possStarts = new Point[l][1];
      possStartsList.reset();
      int i = 0;
      while( !possStartsList.isAtEnd() )
      {
        possStarts[i][0] = new Point( possStartsList.currentData() );
        possStartsList.delete();
        i++;
      }
      return possStarts;
    }
    possStarts = new Point[field.length/gridSize][field[0].length/gridSize];
    for(int x = 0; x < possStarts.length; x++)
    {
      for(int y = 0; y < possStarts[0].length; y++)
      {
      	possStarts[x][y] = new Point( x*gridSize, y*gridSize );
      }
    }
    return possStarts;
  }

  public boolean		isEmptyTrack()								{		return trackIcon == null;			}
  public Dimension 	getTrackSize()   							{   return trackSize;  						}
  public double 		getTerrain(int x, int y)  		{  	return field[x][y];					  }
  public int 				getGridSize()  								{  	return gridSize;						  }
  public void 			setGridSize(int gridSize)			{  	this.gridSize = gridSize;		  }
  public Color 			getGridColor()  							{  	return gridColor;						  }
  public void 			setGridColor(Color gridColor)	{  	this.gridColor = gridColor;  	}
}