package RacingForEngineers.Panels;

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

public class StatusPanel extends JPanel implements RFEConstants
{
	private JLabel mousePosX1L;
	private JLabel mousePosX2L;
	private JLabel mousePosY1L;
	private JLabel mousePosY2L;

	public StatusPanel()
  {
    this.setLayout(null);

  	mousePosX1L = new JLabel("X:");
    mousePosX1L.setBounds((int)GUISize.getWidth()-2*border-120,2,15,26);
  	mousePosX2L = new JLabel();
    mousePosX2L.setBounds((int)GUISize.getWidth()-2*border-105,2,35,26);
    mousePosX2L.setHorizontalAlignment(SwingConstants.RIGHT);
  	mousePosY1L = new JLabel("Y:");
    mousePosY1L.setBounds((int)GUISize.getWidth()-2*border- 65,2,15,26);
  	mousePosY2L = new JLabel();
    mousePosY2L.setBounds((int)GUISize.getWidth()-2*border- 50,2,35,26);
    mousePosY2L.setHorizontalAlignment(SwingConstants.RIGHT);

    this.add(mousePosX1L, null);
    this.add(mousePosX2L, null);
    this.add(mousePosY1L, null);
    this.add(mousePosY2L, null);
  }

  public void setMousePosition(double x, double y)
  {
    String xs = "" + x;
    while(xs.substring(xs.indexOf("."),xs.length()).length() < 3)
      xs += "0";
    String ys = "" + y;
    while(ys.substring(ys.indexOf("."),ys.length()).length() < 3)
      ys += "0";

  	mousePosX2L.setText(xs);
  	mousePosY2L.setText(ys);
  }
}