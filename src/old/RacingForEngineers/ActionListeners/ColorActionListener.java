package RacingForEngineers.ActionListeners;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.Cursor;

import RacingForEngineers.*;
import RacingForEngineers.Panels.*;
import RacingForEngineers.Parts.*;
import RacingForEngineers.ActionListeners.*;

public class ColorActionListener implements MouseInputListener, RFEConstants
{
	private int colorIndex;
  private ChosenColorLabel chosenColorL;
  //private Label[][] colorL;

  private PlayerGUI pgui;

  private ColorChooser cc;

  public ColorActionListener(ColorChooser cc/*PlayerGUI pgui*/, int colorIndex)//, ChosenColorLabel chosenColorL)
  {
  	this.cc = cc;

    //this.pgui = pgui;
    this.colorIndex = colorIndex;
  	//this.chosenColorL = chosenColorL;
  }

  public void mouseClicked(MouseEvent e)
  {
  	cc.setColor(colorIndex);

  	/*
  	if(!pgui.getChosenColors()[colorIndex])
  	{
    	if( chosenColorL.getColor() >= 0)
    	  pgui.getChosenColors()[ chosenColorL.getColor() ] = false;

    	pgui.getChosenColors()[colorIndex] = true;
    	chosenColorL.setColor( colorIndex );
      pgui.updatePlayerPanels();
    }
    */
  }

  public void mouseMoved(MouseEvent e)  {	 }
  public void mouseDragged(MouseEvent e)  {  }
	public void mousePressed(MouseEvent e)  {  }
  public void mouseExited(MouseEvent e)  {  }
  public void mouseEntered(MouseEvent e)  {  }
  public void mouseReleased(MouseEvent e)  {  }

}