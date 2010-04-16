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

public class IncDecButton extends JButton implements RFEConstants, ActionListener
{
	private char incOrDec;
  private IncDecLabel idL;

  public IncDecButton(char incOrDec, IncDecLabel idL)
  {
    if( ( incOrDec != '+' ) && ( incOrDec != '-' ) )
      throw new IllegalArgumentException("Char must be + or -");

  	this.incOrDec = incOrDec;
    this.idL = idL;

    if(incOrDec == '+')
    	this.setIcon(new ImageIcon(imageDir + "plus.jpg"));
    else if(incOrDec == '-')
    	this.setIcon(new ImageIcon(imageDir + "minus.jpg"));

    this.setMargin(new Insets(-5,-5,-5,-5));

    this.addActionListener(this);
  }

	public void actionPerformed(ActionEvent e)
  {
    if(incOrDec == '+')
    	idL.increase();
    else if(incOrDec == '-')
      idL.decrease();
  }
}