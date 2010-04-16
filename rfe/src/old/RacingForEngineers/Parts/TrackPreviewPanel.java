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

public class TrackPreviewPanel extends JPanel implements RFEConstants
{
  private InnerTrackPreviewPanel itpp;
  private String trackName = defTracks[0];

  public TrackPreviewPanel()
  {
  	init();
  }

	public TrackPreviewPanel(String trackName)
  {
  	init();
  	setTrack(trackName);
  }

  public void init()
  {
  	this.setSize(208, 167);
    this.setBorder(BorderFactory.createEtchedBorder());
    this.setLayout(null);

  	itpp = new InnerTrackPreviewPanel();
    itpp.setBounds(border,border,(int)this.getSize().getWidth()-2*border-1,(int)this.getSize().getHeight()-2*border-1);

    this.add(itpp, null);
  }

  public void setTrack(String trackName)
  {
    if(trackName!=null)
    {
			this.trackName = trackName;
	    if(trackName.equals(defTracks[0]))
	      itpp.setTrack(null);
      else
				itpp.setTrack(new ImageIcon(trackName));
    }
    else
    {
    	this.trackName = null;
      itpp.setTrack(null);
    }
  }

  public String getState()
  {
    if(trackName!=null)
    {
	    if(trackName.equals(defTracks[0]))
	      return "The game will randomly choose one of the standard tracks!";
      else
	    	return "The track \"" + trackName + "\" has been chosen.";
    }
    else
    	return "The chosen track could not be loaded!";
  }

  public boolean isRandom()
  {
    if(trackName!=null)
    {
	    if(trackName.equals(defTracks[0]))
	      return true;
      else
	    	return false;
    }
    else
    	return false;
  }
}