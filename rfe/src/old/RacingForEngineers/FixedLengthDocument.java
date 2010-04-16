package RacingForEngineers;

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

public class FixedLengthDocument extends PlainDocument implements RFEConstants
  {
  	private int fixedLength;

    public FixedLengthDocument(int fixedLength)
    {
    	this.fixedLength = fixedLength;
    }

	  public void insertString(int offs, String str, AttributeSet a)throws BadLocationException
	  {
	    if (str == null)
	       return;
	    char[] chars = str.toCharArray();
      int i = 0;
      for( ; i < chars.length-1; i++)
      {
      	if( getLength() + i + 1 >= fixedLength )
      		break;
      }
			while( (getLength() < fixedLength) && ( i >= 0 ) )
	    {
      	super.insertString(offs, "" + chars[i], a);
        i--;
	    }
	  }
	}
