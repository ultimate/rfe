package RacingForEngineers;

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
import RacingForEngineers.Parts.*;

public class PlayerGUI extends JWindow implements RFEConstants
{
  private JButton 		applyB, closeB, resetB;
  private JLabel			descL, titleL;
  private PlayerPanel[] playerP = new PlayerPanel[PlayerCounter.getMaxNumberOfPlayers()];
  private IncDecLabel nopL;
  private boolean[] chosenColors = new boolean[14];

  private String[] playerNames;
  private int[] playerColors;

	public PlayerGUI(JFrame ownerFrame, IncDecLabel nopL, String[] playerNames, int[] playerColors)
  {
  	super(ownerFrame);

    this.nopL = nopL;
    this.playerNames = playerNames;
    this.playerColors = playerColors;
    for(int i = 0; i < chosenColors.length; i++)
    {
    	chosenColors[i] = false;
    }

    this.setSize(PlayerGUISize);

    JPanel myContentPane = new JPanel();
  	myContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),BorderFactory.createEtchedBorder()));
    this.setContentPane(myContentPane);
    this.getContentPane().setLayout(null);
    this.addWindowListener(new PlayerGUIListener(this));

    titleL = new JLabel("--- Game Setup ---");
    titleL.setBounds(5+border, 5+border, (int)PlayerGUISize.getWidth()-10-2*border, 25);
    titleL.setHorizontalAlignment(SwingConstants.CENTER);
    titleL.setFont(new Font(titleL.getFont().getName(), Font.PLAIN, 18));

    for(int i = 0; i < playerP.length; i++)
    {
    	playerP[i] = new PlayerPanel(i, this);
    	playerP[i].setBounds(5+border, border+40+35*i,290,25);
      playerNames[i] = playerP[i].getName();
      playerColors[i] = -1;
      this.getContentPane().add(playerP[i], null);
    }

    setPlayers();

    applyB = new JButton("Confirm Player Configuration");
    applyB.addActionListener(new ApplyPlayerActionListener(this));

    resetB = new JButton("Reset");
    resetB.addActionListener(new ApplyPlayerActionListener(this));

    descL = new JLabel("---");
    descL.setBorder(BorderFactory.createEtchedBorder());

    closeB = new JButton();
    closeB.setIcon(new ImageIcon(imageDir + "closeIcon.jpg"));
    closeB.setBounds((int)this.getWidth()-2*border-27,border+4,26,25);
    closeB.addActionListener(new ApplyPlayerActionListener(this));
    closeB.setMargin(new Insets(-5,-5,-5,-5));

    mySetVisible(true);
    addMouseMotionListenersToComponents();

    this.getContentPane().add(titleL, null);
    this.getContentPane().add(applyB, null);
    this.getContentPane().add(resetB, null);
    this.getContentPane().add(closeB, null);
    this.getContentPane().add(descL, null);
  }

  public void addMouseMotionListenersToComponents()
  {
    this.addMouseMotionListener(new PlayerGUIListener(this));
    applyB.addMouseMotionListener(new PlayerGUIListener(this));
    resetB.addMouseMotionListener(new PlayerGUIListener(this));
    closeB.addMouseMotionListener(new PlayerGUIListener(this));
    descL.addMouseMotionListener(new PlayerGUIListener(this));
	}

  public void updateDescription(MouseEvent e)
  {
  	Object s = e.getSource();
		if( s.equals(applyB) )
    	descL.setText("Close window.");
    else if( s.equals(resetB) )
    	descL.setText("Reset player names and colors to default.");
    else if( s.equals(closeB) )
    	descL.setText("Close window and discard changes.");
    else if( s.equals(descL) )
    	descL.setText("Move mouse to a component in this window to get description.");
    else
    	descL.setText("This feature will come in a future-version!");
  }

  public void setPlayers()
  {
    for(int i = 0; i < playerP.length; i++)
    {
    	playerP[i].setName(playerNames[i]);
    	if(i < nopL.getValue())
      {
      	playerP[i].setVisible(true);
	      playerP[i].setColor(playerColors[i]);
      }
      else
      {
      	playerP[i].setVisible(false);
      	playerP[i].reset();
      }
    }
    updatePlayerPanels();
  }

  public void updatePlayerPanels()
  {
    for(int i = 0; i < nopL.getValue(); i++)
    {
      playerP[i].updateColorChooser();
    }
  }

  public void resetPlayerPanels()
  {
    for(int i = 0; i < chosenColors.length; i++)
    {
      chosenColors[i] = false;
    }
    for(int i = 0; i < playerP.length; i++)
    {
      playerP[i].reset();
    }
    for(int i = 0; i < playerP.length; i++)
    {
    	if(i < nopL.getValue())
      {
      	playerP[i].setVisible(true);
      }
      else
      {
      	playerP[i].setVisible(false);
      }
    }
    updatePlayerPanels();
  }

  public boolean[] getChosenColors()
  {
  	return chosenColors;
  }

  public void initPlayers()
  {
  	for(int i = 0; i < PlayerCounter.getMaxNumberOfPlayers(); i++)
    {
    	playerNames[i] = playerP[i].getName();
      playerColors[i] = playerP[i].getColor();
    }
  }

  public void mySetVisible(boolean visible)
  {
    if(visible)
    {
			setPlayers();
    	int p = nopL.getValue();
    	this.setSize((int)PlayerGUISize.getWidth(), 2*border+40+35+35*(p+1));
      applyB.setBounds(5+border, border+40+35*p+5, 190, 25);
      resetB.setBounds(5+border+190+15, border+40+35*p+5, 90, 25);
      descL.setBounds(5+border, border+40+40+35*p, (int)this.getWidth()-10-2*border+1, 25);
    }
    this.setVisible(visible);
  }
}