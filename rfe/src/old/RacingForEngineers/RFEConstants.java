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

public interface RFEConstants
{
	String[] defTracks = { "? random ?", "default0.png", "default1.png", "default2.png" };    // von platte lesen in V2
  String trackDir = "Data/Tracks/";
  String imageDir = "Data/Images/";
  Object syncobject = new Object();
  Dimension GUISize 				= new Dimension(1200, 900);
  Dimension NewGameGUISize 	= new Dimension( 400, 289);
  Dimension PlayerGUISize 	= new Dimension( 313, 291);
  Dimension ProfiSetupGUISize 	= new Dimension( 400, 150);
  Dimension TrackToolGUISize 	= new Dimension( 458, 500);
  Color bgColor = new Color(238,238,238);
  int barHeight = 30;
  int border = 4;
  Game game = new Game();
}