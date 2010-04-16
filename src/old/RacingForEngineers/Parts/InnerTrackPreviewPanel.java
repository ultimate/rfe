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

public class InnerTrackPreviewPanel extends JPanel implements RFEConstants
{
  private ImageIcon trackIcon = null;

  public InnerTrackPreviewPanel()
  {
  }

	public InnerTrackPreviewPanel(ImageIcon trackIcon)
  {
		setTrack(trackIcon);
  }

  public void setTrack(ImageIcon trackIcon)
  {
    this.trackIcon = trackIcon;
    repaint();
  }

  public void paint(Graphics g)
  {
    if(trackIcon!=null)
    	g.drawImage( trackIcon.getImage(), 0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight(), null);
    else
    {
    	g.drawImage( (new ImageIcon(imageDir + "random.png")).getImage(), 0, 0, (int)this.getSize().getWidth(), (int)this.getSize().getHeight(), null);
      /*
    	g.setColor(bgColor);
    	g.fillRect(0, 0, 300, 300);                
      */
    }
	}
}