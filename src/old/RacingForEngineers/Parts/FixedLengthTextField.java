package RacingForEngineers.Parts;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.Cursor;
import javax.swing.text.*;

import RacingForEngineers.*;
import RacingForEngineers.Panels.*;
import RacingForEngineers.ActionListeners.*;

public class FixedLengthTextField extends JTextField
{
	private int fixedLength;

  public FixedLengthTextField(int fixedLength)
  {
  	super();
    this.fixedLength = fixedLength;
    setDocument(createDefaultModel());
  }

	protected Document createDefaultModel() {
	  return new FixedLengthDocument(fixedLength);
	}
}