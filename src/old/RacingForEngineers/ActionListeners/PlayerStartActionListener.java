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

public class PlayerStartActionListener implements ActionListener, RFEConstants
{
	private PlayerGUI pgui;

  public PlayerStartActionListener(JFrame ownerFrame, IncDecLabel nopL, String[] playerNames, int[] playerColors)
  {
  	pgui = new PlayerGUI(ownerFrame, nopL, playerNames, playerColors);
    pgui.setSize(PlayerGUISize);
    pgui.setLocation(300,300);
		pgui.setVisible(false);
  }

  public void actionPerformed(ActionEvent e)
  {
    pgui.mySetVisible(true);
  }
}