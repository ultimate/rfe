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

public class ColorLabel extends Label implements MouseInputListener, RFEConstants
{
  private Color c = null;

  public ColorLabel(Font f)
  {
  	super();
  	setColor(null);
		setAlignment(Label.CENTER);
    setFont( f );
  }

	public void setColor(Color c)
  {
    this.c = c;
  	if( this.c == null )
    {
      setBackground( bgColor );
      setText("?");
    }
    else
  	{
    	setBackground( c );
      setText("");
    }
  }

  public Color getColor()
  {
  	return c;
  }

  public void resetColor()
  {
  	setColor(null);
  }

  public void mouseClicked(MouseEvent e)  {  	resetColor();  }
  public void mouseMoved(MouseEvent e)  {	 }
  public void mouseDragged(MouseEvent e)  {  }
	public void mousePressed(MouseEvent e)  {  }
  public void mouseExited(MouseEvent e)  {  }
  public void mouseEntered(MouseEvent e)  {  }
  public void mouseReleased(MouseEvent e)  {  }

}