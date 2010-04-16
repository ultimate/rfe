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

public class RFEGUI extends JWindow implements RFEConstants
{
  private GameFieldPanel gameP;
  private MenuPanel menuP;
  private StatusPanel statusP;
  private OptionPanel optionP;
  private JFrame ownerFrame;

	public RFEGUI(JFrame ownerFrame)
  {
  	super(ownerFrame);
    this.ownerFrame = ownerFrame;
  	init();
  }

  public void init()
  {
    this.setSize(GUISize);
    this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    //Toolkit.createCustomCursor(java.awt.Image, java.awt.Point, java.lang.String),

    JPanel myContentPane = new JPanel();
  	myContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),BorderFactory.createEtchedBorder()));
    this.setContentPane(myContentPane);
    this.getContentPane().setLayout(null);

    //game.getPlayerCounter().setNumberOfPlayers(2);
/*    try
    {
    	game.startNewGame(2, "Data/Tracks/rfe.png" );
    }
    catch(Exception e)
    {
    	e.printStackTrace();
    	System.out.println("Error in Track!");
      return;
    }
    for(int i = 0; i < game.getPlayerCounter().getNumberOfPlayers(); i++)
    {
      game.getPlayers()[i] = new Car("Spieler " + i,i,new Point((5+2*i)*game.getTrack().getGridSize(),2*game.getTrack().getGridSize()));
    } */


    /* test
    Car[] c = Car.randomInitCars(8);
    for(int i = 0; i < 8; i++)
    {
    	System.out.println(c[i].getPlayerName() + ", Color: " + c[i].getCarColor());
		} */

		gameP = new GameFieldPanel(this);
 		menuP = new MenuPanel(ownerFrame);
    statusP = new StatusPanel();
    optionP = new OptionPanel();

		gameP.setBounds(border,barHeight+border,(int)game.getTrack().getTrackSize().getWidth(),(int)game.getTrack().getTrackSize().getHeight());
    gameP.setBorder(BorderFactory.createEtchedBorder());

    menuP.setBounds(border,border,(int)GUISize.getWidth()-2*border,barHeight);
    menuP.setBorder(BorderFactory.createEtchedBorder());

    statusP.setBounds(border,(int)GUISize.getHeight()-barHeight-border,(int)GUISize.getWidth()-2*border,barHeight);
    statusP.setBorder(BorderFactory.createEtchedBorder());

    optionP.setBounds(border+1+(int)game.getTrack().getTrackSize().getWidth(),barHeight+border,(int)(GUISize.getWidth()-2*border-game.getTrack().getTrackSize().getWidth()-1),(int)game.getTrack().getTrackSize().getHeight()+1);
    optionP.setBorder(BorderFactory.createEtchedBorder());

    this.getContentPane().add(gameP, null);
    this.getContentPane().add(menuP, null);
    this.getContentPane().add(statusP, null);
    this.getContentPane().add(optionP, null);
  }

  public GameFieldPanel getGameFieldPanel()
  {
    return gameP;
  }

  public StatusPanel getStatusPanel()
  {
    return statusP;
  }

  public MenuPanel getMenuPanel()
  {
    return menuP;
  }
}