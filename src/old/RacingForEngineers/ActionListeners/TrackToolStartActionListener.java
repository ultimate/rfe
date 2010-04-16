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

public class TrackToolStartActionListener implements ActionListener, RFEConstants
{
  private TrackToolGUI ttgui;

  public TrackToolStartActionListener(JFrame ownerFrame)
  {
  	ttgui = new TrackToolGUI(ownerFrame);
    ttgui.setSize(TrackToolGUISize);
    ttgui.setLocation(500,200);
    ttgui.setVisible(false);
  }

  public TrackToolStartActionListener(TrackToolGUI ttgui)
  {
  	this.ttgui = ttgui;
  }

  public void actionPerformed(ActionEvent e)
  {
    ttgui.setVisible(!ttgui.isVisible());
  }
}