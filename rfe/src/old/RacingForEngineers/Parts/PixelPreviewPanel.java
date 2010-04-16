package RacingForEngineers.Parts;

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

public class PixelPreviewPanel extends JPanel implements RFEConstants
{
	private TrackToolGUI ttgui;

  public PixelPreviewPanel(TrackToolGUI ttgui)
  {
  	this.ttgui = ttgui;
  }

	public void paint(Graphics g)
  {
  	ttgui.drawPixels(g);
  }
}