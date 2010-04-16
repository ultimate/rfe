package RacingForEngineers.Parts;

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

public class IncDecLabel extends JLabel implements RFEConstants
{
	private int max, min, value, step;

  public IncDecLabel(int min, int max, int value)
	{
  	this.max = max;
    this.min = min;
    this.value = value;
    this.setText("" + value);
    this.step = 1;
  }

  public IncDecLabel(int min, int max, int value, int step)
  {
  	this.max = max;
    this.min = min;
    this.value = value;
    this.setText("" + value);
    this.step = step;
  }

  public int 	getValue() 				{  return value;  	}
  public int 	getMax()  				{  return max;  		}
  public int 	getMin() 					{  return min;  		}
  public int 	getStep() 				{  return step;  		}
  public void setMax(int max) 	{  this.max = max;  }
  public void setMin(int min) 	{  this.min = min;	}
  public void setStep(int min) 	{  this.step = step;	}

  public boolean increase()
  {
    if(value <= max-step)
    {
    	value = value + step;
      this.setText("" + value);
      return true;
    }
    return false;
  }

  public boolean decrease()
  {
    if(value >= min+step)
    {
    	value = value - step;
      this.setText("" + value);
      return true;
    }
    return false;
  }
}