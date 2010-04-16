package RacingForEngineers.ActionListeners;

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

public class TrackToolGUIListener implements WindowListener, RFEConstants, MouseMotionListener, ActionListener
{
	private TrackToolGUI ttgui;

  public TrackToolGUIListener(TrackToolGUI ttgui)
  {
  	this.ttgui = ttgui;
  }

  public void windowClosing(WindowEvent e) {  }
  public void windowActivated(WindowEvent e)  {  }
  public void windowClosed(WindowEvent e)  {  }
  public void windowDeactivated(WindowEvent e)  {  }
  public void windowDeiconified(WindowEvent e)  {  }
  public void windowIconified(WindowEvent e)  {  }
  public void windowOpened(WindowEvent e)  {  }

  public void mouseDragged(MouseEvent e)  {    mouseMoved(e);  }

  public void mouseMoved(MouseEvent e)
  {
  	ttgui.updateDescription(e);
  }

  public void actionPerformed(ActionEvent e)
  {
  	ttgui.updateColorDefiners(e);
    ttgui.updateModeButtons(e);
  }
}