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

public class ApplyStartActionListener implements ActionListener, RFEConstants
{
	private NewGameGUI nggui;

  public ApplyStartActionListener(NewGameGUI nggui)
  {
  	this.nggui = nggui;
  }

  public void actionPerformed(ActionEvent e)
  {
  	if( !((JButton)e.getSource()).getText().equals("") )
    {                      
      nggui.initNewGame();
    }
    nggui.setVisible(false);
  }
}