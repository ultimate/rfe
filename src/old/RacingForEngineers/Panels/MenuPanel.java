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

public class MenuPanel extends JPanel implements RFEConstants
{
  private JButton closeB = new JButton();
  private JButton startB = new JButton();
  private JButton trackToolB = new JButton();

	public MenuPanel(JFrame ownerFrame)
  {
  	this.setLayout(null);

    startB.setText("Start New Race");
    startB.setBounds(2,2,100,25);
    startB.addActionListener(new StartActionListener(ownerFrame));
    startB.setMargin(new Insets(-5,-5,-5,-5));

    trackToolB.setText("Track Tool");
    trackToolB.setBounds(102,2,100,25);
    trackToolB.addActionListener(new TrackToolStartActionListener(ownerFrame));
    trackToolB.setMargin(new Insets(-5,-5,-5,-5));

    closeB.setIcon(new ImageIcon(imageDir + "closeIcon.jpg"));
    closeB.setBounds((int)GUISize.getWidth()-2*border-29,2,26,25);
    closeB.addActionListener(new CloseActionListener());
    closeB.setMargin(new Insets(-5,-5,-5,-5));

		this.add(startB, null);
		this.add(trackToolB, null);
    this.add(closeB, null);
  }
}