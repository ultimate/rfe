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
import RacingForEngineers.Parts.*;
import RacingForEngineers.ActionListeners.*;

public class PlayerPanel extends JPanel implements RFEConstants
{
 	private JTextField nameTF;
  private ColorChooser cc;

  private int index;

	public PlayerPanel(int index, PlayerGUI pgui)
  {
  	this.index = index;

  	this.setLayout(null);

    nameTF = new JTextField("Player " + index);
    nameTF.setBounds(0,0,150,26);

    cc = new ColorChooser(index, pgui);
    cc.setBounds(165,0,145,26);

    this.add(nameTF, null);
    this.add(cc, null);
  }

  public void updateColorChooser()
  {
  	cc.updateColorLabels();
  }

  public void reset()
  {
    nameTF.setText("Player " + index);
  	cc.resetChosenColorLabel();
  }

  public String getName()
  {
  	return nameTF.getText();
  }

  public void setName(String name)
  {
  	nameTF.setText(name);
  }

  public int getColor()
  {
  	return cc.getColor();
  }

  public void setColor(int colorIndex)
  {
  	cc.setColor(colorIndex);
  }            
}