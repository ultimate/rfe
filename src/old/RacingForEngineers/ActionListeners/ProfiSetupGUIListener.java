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

public class ProfiSetupGUIListener implements WindowListener, RFEConstants, MouseMotionListener
{
	private ProfiSetupGUI psgui;

  public ProfiSetupGUIListener(ProfiSetupGUI psgui)
  {
  	this.psgui = psgui;
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
  	psgui.updateDescription(e);
  }
}