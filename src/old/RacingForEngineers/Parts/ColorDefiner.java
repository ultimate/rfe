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

public class ColorDefiner extends JPanel implements RFEConstants, ActionListener
{
  private JLabel textL;
  private ColorLabel colorL;
  private JTextField colorTF;

  public ColorDefiner(String text)
  {
  	this.setLayout(null);

    textL = new JLabel(text);
    textL.setBounds(0,0,60,25);

    colorTF = new JTextField("#hex / r,g,b");
    colorTF.setBounds(65,0,100,25);
    colorTF.addActionListener(this);

    colorL = new ColorLabel( textL.getFont() );
    colorL.setBounds(170,0,25,25);
    colorL.setColor( null );

    this.add(textL, null);
    this.add(colorTF, null);
    this.add(colorL, null);
  }

  public void actionPerformed(ActionEvent e)
  {
    colorL.setText("");
    colorL.setBackground( bgColor );

    int c[] = new int[3];
  	String colorStr = colorTF.getText();
    if( colorStr.length() == 0 )
    {
      colorL.setColor(null);
      return;
		}
    else if( colorStr.charAt(0) == '#' )
    {
    	colorStr = colorStr.substring(1,colorStr.length());
      if( !colorStr.matches("[0-9a-fA-F]{6}") )
      {
      	colorL.setColor(null);
        return;
      }
      for(int i = 0; i < 3; i++)
      {
      	c[i] = Integer.parseInt(colorStr.substring(2*i,2*(i+1)),16);
      }
    }
    else
    {
    	String[] rgb = colorStr.split(",");
      if( rgb.length != 3 )
      {
      	colorL.setColor(null);
        return;
      }
      for(int i = 0; i < rgb.length; i++)
      {
      	try
        {
        	c[i] = Integer.parseInt(rgb[i]);
        }
        catch(Exception ex)
        {
	        colorL.setColor(null);
	        return;
        }
        if( (c[i] < 0) || (c[i] > 255) )
        {
	        colorL.setColor(null);
	        return;
        }
      }
    }
    colorL.setColor(new Color(c[0],c[1],c[2]));
  }

  public void setText(String text)
  {
  	textL.setText(text);
  }

  public Color getColor()
  {
  	return colorL.getColor();
  }
}