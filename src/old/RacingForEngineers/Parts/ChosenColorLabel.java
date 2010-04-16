package RacingForEngineers.Parts;

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
import RacingForEngineers.ActionListeners.*;

public class ChosenColorLabel extends Label implements MouseInputListener, RFEConstants
{
  private int colorIndex = -1;
  private PlayerGUI pgui;

  public ChosenColorLabel(PlayerGUI pgui)
  {
		this.pgui = pgui;
    this.addMouseListener(this);
  }

	public void setColor(int colorIndex)
  {
  	setBackground( Car.playerColors[colorIndex] );
    this.colorIndex = colorIndex;
  }

  public int getColor()
  {
  	return colorIndex;
  }

  public void resetColor()
  {
  	if(colorIndex >= 0)
    {
      pgui.getChosenColors()[colorIndex] = false;
  		setBackground(bgColor);
    	colorIndex = -1;
    }
    pgui.updatePlayerPanels();
  }

  public void mouseClicked(MouseEvent e)  {  	resetColor();  }
  public void mouseMoved(MouseEvent e)  {	 }
  public void mouseDragged(MouseEvent e)  {  }
	public void mousePressed(MouseEvent e)  {  }
  public void mouseExited(MouseEvent e)  {  }
  public void mouseEntered(MouseEvent e)  {  }
  public void mouseReleased(MouseEvent e)  {  }

}