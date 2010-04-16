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

public class CloseActionListener implements ActionListener, RFEConstants
{
  public void actionPerformed(ActionEvent e)
  {
    synchronized(syncobject)
    {
      syncobject.notify();
    }
  }
}