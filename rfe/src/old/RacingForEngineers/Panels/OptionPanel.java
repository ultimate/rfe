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

public class OptionPanel extends JPanel implements RFEConstants
{
	public OptionPanel()
  {
  	this.setLayout(null);

    JTextField tf = new JTextField("laskgj");
    tf.setBounds(20,20,100,50);
    this.add(tf, null);
  }
}