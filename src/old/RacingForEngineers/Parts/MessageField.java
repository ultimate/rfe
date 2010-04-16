package RacingForEngineers.Parts;

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
import RacingForEngineers.ActionListeners.*;

public class MessageField extends JPanel implements RFEConstants
{
	private FixedLengthTextField messageTF;

  public MessageField(int messageLength)
  {
  	this.setLayout(null);

  	messageTF = new FixedLengthTextField(messageLength);
    messageTF.setBounds(0,0,300,25);

    this.add(messageTF, null);
  }

  public String getMessage()
  {
  	return messageTF.getText();
  }
}