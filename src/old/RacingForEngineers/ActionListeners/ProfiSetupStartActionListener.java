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

public class ProfiSetupStartActionListener implements ActionListener, RFEConstants
{
	private ProfiSetupGUI psgui;

  public ProfiSetupStartActionListener(JFrame ownerFrame)
  {
  	psgui = new ProfiSetupGUI(ownerFrame);
    psgui.setSize(ProfiSetupGUISize);
    psgui.setLocation(400,400);
    psgui.setVisible(false);
  }

  public void actionPerformed(ActionEvent e)
  {
    psgui.setVisible(true);
  }
}