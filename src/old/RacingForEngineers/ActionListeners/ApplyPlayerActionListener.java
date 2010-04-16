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

public class ApplyPlayerActionListener implements ActionListener, RFEConstants
{
	private PlayerGUI pgui;

  public ApplyPlayerActionListener(PlayerGUI pgui)
  {
  	this.pgui = pgui;
  }

  public void actionPerformed(ActionEvent e)
  {
  	if( ((JButton)e.getSource()).getText().equals("") )
    {
    	// simply close window
	    pgui.mySetVisible(false);
    }
    else if( ((JButton)e.getSource()).getText().equals("Reset") )
    {
    	// reset
      pgui.resetPlayerPanels();
    }
    else if( ((JButton)e.getSource()).getText().equals("Confirm Player Configuration") )
    {
    	// apply
      pgui.initPlayers();
	    pgui.mySetVisible(false);
    }
  }
}