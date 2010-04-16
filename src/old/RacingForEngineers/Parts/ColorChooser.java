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
import RacingForEngineers.Parts.*;
import RacingForEngineers.ActionListeners.*;

public class ColorChooser extends JPanel implements RFEConstants
{
  private Label[][] colorL = new Label[7][2];
  private ChosenColorLabel chosenColorL;

  private int index;
  private PlayerGUI pgui;

  public ColorChooser(int index, PlayerGUI pgui)
  {
    this.index = index;
    this.pgui = pgui;

  	this.setLayout(null);

    chosenColorL = new ChosenColorLabel(pgui);
    chosenColorL.setBounds(0,0,25,25);
    this.add(chosenColorL, null);

    for(int x = 0; x < colorL.length; x++)
    {
    	for(int y = 0; y < colorL[0].length; y++)
      {
      	int colorIndex = x*2+y;
      	colorL[x][y] = new Label();
        colorL[x][y].setBounds(40+x*13,y*13,12,12);
        colorL[x][y].setBackground( Car.playerColors[colorIndex] );
        colorL[x][y].addMouseListener(new ColorActionListener(this, colorIndex));
        this.add(colorL[x][y], null);
      }
		}

    updateColorLabels();
  }

  public void updateColorLabels()
  {
    for(int x = 0; x < colorL.length; x++)
  	{
  		for(int y = 0; y < colorL[0].length; y++)
     	{
     		int c = 2*x+y;
       	colorL[x][y].setVisible(!pgui.getChosenColors()[c]);
     	}
		}
  }

  public void resetChosenColorLabel()
  {
  	chosenColorL.resetColor();
  }

  public void setColor(int colorIndex)
  {
    if( colorIndex < 0)
    {
    	resetChosenColorLabel();
      updateColorLabels();
    }
  	else if(!pgui.getChosenColors()[colorIndex])
  	{
    	if( chosenColorL.getColor() >= 0)
    	  pgui.getChosenColors()[ chosenColorL.getColor() ] = false;

    	pgui.getChosenColors()[colorIndex] = true;
    	chosenColorL.setColor( colorIndex );
      pgui.updatePlayerPanels();
    }
  }

  public int getColor()
  {
  	return chosenColorL.getColor();
  }
}