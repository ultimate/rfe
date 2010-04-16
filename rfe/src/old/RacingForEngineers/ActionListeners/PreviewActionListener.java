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
import RacingForEngineers.ActionListeners.*;
import RacingForEngineers.Parts.*;

public class PreviewActionListener implements ActionListener, RFEConstants
{
	private TrackPreviewPanel tpP;
  private JComboBox trackCB;
  private RFETrack tempTrack;

  public PreviewActionListener(TrackPreviewPanel tpP, JComboBox trackCB, RFETrack tempTrack)
  {
  	this.tpP = tpP;
    this.trackCB = trackCB;
    this.tempTrack = tempTrack;
  }

  public void actionPerformed(ActionEvent e)
  {
  	String trackName = (String)trackCB.getSelectedItem();
    if( trackName.equals(defTracks[0]) )
    	tpP.setTrack(trackName);
    else
    {
    	trackName = trackDir + trackName;
	    try
	    {
	      tempTrack.setTrack(trackName);
	      tpP.setTrack(trackName);
	    }
	    catch(Exception ex)
	    {
      	ex.printStackTrace();
	      tpP.setTrack(null);
	    }
    }
  }
}