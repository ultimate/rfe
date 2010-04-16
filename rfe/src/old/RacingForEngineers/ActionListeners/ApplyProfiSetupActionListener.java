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

public class ApplyProfiSetupActionListener implements ActionListener, RFEConstants
{
	private ProfiSetupGUI psgui;

  public ApplyProfiSetupActionListener(ProfiSetupGUI psgui)
  {
  	this.psgui = psgui;
  }

  public void actionPerformed(ActionEvent e)
  {
  	if( ((JButton)e.getSource()).getText().equals("") )
    {
    	// simply close window
	    psgui.setVisible(false);
    }
    else if( ((JButton)e.getSource()).getText().equals("OK") )
    {
    	// apply
	    psgui.setVisible(false);
    }
  }
}