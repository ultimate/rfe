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

public class Terrain implements RFEConstants
{
	private double index;
  private String defStr;
  private String colStr;
  private String name;
  private int r,g,b;

  public Terrain(double index, String defStr, String name)
  {
  	this.index = index;
    this.defStr = defStr;
    this.colStr = defStr;
    this.name = name;
    initRGB();
  }

  public double getDistance(String fieldColor)
  {
    if( fieldColor.equals("------") || colStr.equals("------") )
    	return Double.POSITIVE_INFINITY;

    int fieldR = Integer.parseInt(fieldColor.substring(0,2),16);
    int fieldG = Integer.parseInt(fieldColor.substring(2,4),16);
    int fieldB = Integer.parseInt(fieldColor.substring(4,6),16);

    return Math.sqrt( (fieldR-r)*(fieldR-r) + (fieldG-g)*(fieldG-g) + (fieldB-b)*(fieldB-b) );
	}

  public void initRGB()
  {
  	if( !colStr.equals("------") )
    {
    	this.r = Integer.parseInt(colStr.substring(0,2),16);
    	this.g = Integer.parseInt(colStr.substring(2,4),16);
    	this.b = Integer.parseInt(colStr.substring(4,6),16);
    }
    else
    {
    	this.r = -1;
    	this.g = -1;
      this.b = -1;
    }
  }

  public void 	reset()  												{   this.colStr = this.defStr;  initRGB(); }
  public void 	setColorString(String colStr)  	{   this.colStr = colStr; 			initRGB(); }
  public String getColorString()								{  	return colStr;  					}
  public String getDefaultString()  						{  	return defStr;  					}
  public double	getIndex()  										{  	return index;  						}
  public String	getName()  											{  	return name;  						}

  public int[]  getRGB()
  {
    int[] rgb = {r,g,b};
    return rgb;
 	}
}