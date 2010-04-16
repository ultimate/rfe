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

public class StartActionListener implements ActionListener, RFEConstants
{
  private NewGameGUI nggui;

  public StartActionListener(JFrame ownerFrame)
  {
  	nggui = new NewGameGUI(ownerFrame);
    nggui.setSize(NewGameGUISize);
    nggui.setLocation(200,200);
    nggui.setVisible(false);
  }

  public void actionPerformed(ActionEvent e)
  {
    nggui.setVisible(true);
  }
}