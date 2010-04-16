package RacingForEngineers;

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

public class Start implements RFEConstants
{
  public static void main(String args[])
  {
    System.out.println("Initializing...");
    //Look-and-Feel setzen
    try
    {
      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
      System.out.println("Look-And-Feel set to \"Metal\"");
    }
    catch (Exception e)
    {
      System.out.println("Look-And-Feel could not be set! - Using Standard instead!");
    }

    JFrame ownerFrame = new JFrame();
    ownerFrame.setTitle("Racing for Engineers - V 1.0 - (c)Julian Verkin & Sergei Buinov");
    ownerFrame.setIconImage( (new ImageIcon("Data/Images/icon.jpg")).getImage() );
    ownerFrame.setSize(0,0);
    ownerFrame.setLocation(-200,-200);
    ownerFrame.setVisible(true);

    RFEGUI rfegui = new RFEGUI(ownerFrame);
    rfegui.setSize(GUISize);
    rfegui.setLocation(50,50);
    rfegui.setVisible(true);

    rfegui.addWindowListener(new RFEGUIListener());
    System.out.println("Hello");

		synchronized(syncobject)
    {
      try
      {
        syncobject.wait();
      }
      catch(Exception e)
      {
      }
    }

    System.out.println("Bye!");
    rfegui.setVisible(false);
		rfegui.dispose();
		System.exit(0);
  }
}