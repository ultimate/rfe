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

public class NewGameGUI extends JWindow implements RFEConstants
{
  private JLabel 			trackL, roundsL, playerL, titleL, descL;
  private IncDecLabel norL, nopL;
  private JButton 		trackB, profiB, playerCustB, applyB, closeB;
  private IncDecButton norIncB, norDecB, nopIncB, nopDecB;
  private JComboBox   trackCB;
  private TrackPreviewPanel tpP;

  private String[] playerNames = new String[PlayerCounter.getMaxNumberOfPlayers()];
  private int[] playerColors = new int[PlayerCounter.getMaxNumberOfPlayers()];
  private boolean randomPlayers = true;
  private RFETrack tempTrack = new RFETrack();

	public NewGameGUI(JFrame ownerFrame)
  {
  	super(ownerFrame);

    this.setSize(NewGameGUISize);

    JPanel myContentPane = new JPanel();
  	myContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),BorderFactory.createEtchedBorder()));
    this.setContentPane(myContentPane);
    this.getContentPane().setLayout(null);
  	this.addWindowListener(new NewGameGUIListener(this));

    titleL = new JLabel("--- Game Setup ---");
    titleL.setBounds(5+border, 5+border, (int)NewGameGUISize.getWidth()-10-2*border, 25);
    titleL.setHorizontalAlignment(SwingConstants.CENTER);
    titleL.setFont(new Font(titleL.getFont().getName(), Font.PLAIN, 18));

    trackL = new JLabel("Track:");
    trackL.setBounds(10+border, 40+border, 80, 25);

    tpP = new TrackPreviewPanel();
    tpP.setBounds(180+border, 75+border, 208, 167);
    tpP.setBorder(BorderFactory.createEtchedBorder());

    trackCB = new JComboBox(defTracks);
    trackCB.setBounds(85+border, 40+border, 217, 25);
    trackCB.setEditable(true);
    trackCB.addActionListener(new PreviewActionListener(tpP, trackCB, tempTrack));

    trackB = new JButton("Preview");
    trackB.setBounds(307+border, 40+border, 80, 25);
    trackB.addActionListener(new PreviewActionListener(tpP, trackCB, tempTrack));


    roundsL = new JLabel("Rounds:");
    roundsL.setBounds(10+border, 75+border, 110, 25);

    norL = new IncDecLabel(1,RFETrack.maxRounds,1); // evtl zahlen irgendwo herlesen
    norL.setBounds(125+border, 75+border, 25, 25);
    norL.setHorizontalAlignment(SwingConstants.RIGHT);

    norIncB = new IncDecButton('+', norL);
    norIncB.setBounds(155+border, 75+border, 20, 12);

    norDecB = new IncDecButton('-', norL);
    norDecB.setBounds(155+border, 87+border, 20, 12);

    playerL = new JLabel("Number of players:");
    playerL.setBounds(10+border, 109+border, 110, 25);

    nopL = new IncDecLabel(1,PlayerCounter.getMaxNumberOfPlayers(),2);      // evtl zahlen irgendwo herlesen
    nopL.setBounds(125+border, 109+border, 25, 25);
    nopL.setHorizontalAlignment(SwingConstants.RIGHT);

    nopIncB = new IncDecButton('+', nopL);
    nopIncB.setBounds(155+border, 109+border, 20, 12);

    nopDecB = new IncDecButton('-', nopL);
    nopDecB.setBounds(155+border, 121+border, 20, 12);

    playerCustB = new JButton("Player-Setup");
    playerCustB.setBounds(5+border, 143+border, 170, 25);
    playerCustB.addActionListener(new PlayerStartActionListener(ownerFrame, nopL, playerNames, playerColors));

    profiB = new JButton("Professional Setup");
    profiB.setBounds(5+border, 177+border, 170, 25);
    profiB.addActionListener(new ProfiSetupStartActionListener(ownerFrame));

    applyB = new JButton("Apply & Start Game");
    applyB.setBounds(5+border, 216+border, 170, 25);
    applyB.addActionListener(new ApplyStartActionListener(this));

    descL = new JLabel("---");
    descL.setBounds(5+border, 251+border, (int)NewGameGUISize.getWidth()-10-2*border+1, 25);
    descL.setBorder(BorderFactory.createEtchedBorder());

    closeB = new JButton();
    closeB.setIcon(new ImageIcon(imageDir + "closeIcon.jpg"));
    closeB.setBounds((int)NewGameGUISize.getWidth()-2*border-27,border+4,26,25);
    closeB.addActionListener(new ApplyStartActionListener(this));
    closeB.setMargin(new Insets(-5,-5,-5,-5));

    addMouseMotionListenersToComponents();
    trackB.doClick();

    this.getContentPane().add(titleL, null);
    this.getContentPane().add(trackL, null);
    this.getContentPane().add(trackB, null);
    this.getContentPane().add(trackCB, null);
    this.getContentPane().add(roundsL, null);
    this.getContentPane().add(norL, null);
    this.getContentPane().add(norIncB, null);
    this.getContentPane().add(norDecB, null);
    this.getContentPane().add(playerL, null);
    this.getContentPane().add(nopL, null);
    this.getContentPane().add(nopIncB, null);
    this.getContentPane().add(nopDecB, null);
    this.getContentPane().add(playerCustB, null);
    this.getContentPane().add(profiB, null);
    this.getContentPane().add(applyB, null);
    this.getContentPane().add(closeB, null);
    this.getContentPane().add(tpP, null);
    this.getContentPane().add(descL, null);
  }

  public void addMouseMotionListenersToComponents()
  {
    this.addMouseMotionListener(new NewGameGUIListener(this));
    trackL.addMouseMotionListener(new NewGameGUIListener(this));
    trackB.addMouseMotionListener(new NewGameGUIListener(this));
    trackCB.addMouseMotionListener(new NewGameGUIListener(this));
    roundsL.addMouseMotionListener(new NewGameGUIListener(this));
    norL.addMouseMotionListener(new NewGameGUIListener(this));
    norIncB.addMouseMotionListener(new NewGameGUIListener(this));
    norDecB.addMouseMotionListener(new NewGameGUIListener(this));
    playerL.addMouseMotionListener(new NewGameGUIListener(this));
    nopL.addMouseMotionListener(new NewGameGUIListener(this));
    nopIncB.addMouseMotionListener(new NewGameGUIListener(this));
    nopDecB.addMouseMotionListener(new NewGameGUIListener(this));
    playerCustB.addMouseMotionListener(new NewGameGUIListener(this));
    profiB.addMouseMotionListener(new NewGameGUIListener(this));
    applyB.addMouseMotionListener(new NewGameGUIListener(this));
    closeB.addMouseMotionListener(new NewGameGUIListener(this));
    tpP.addMouseMotionListener(new NewGameGUIListener(this));
    descL.addMouseMotionListener(new NewGameGUIListener(this));
	}

  public void updateDescription(MouseEvent e)
  {
  	Object s = e.getSource();
  	if( s.equals(trackL) || s.equals(trackCB) )
    	descL.setText("Choose a track stored in \"Data/Tracks/...\" by filename.");
    else if( s.equals(trackB) )
    	descL.setText("Click to load track or press return in the textfield.");
    else if( s.equals(roundsL) || s.equals(norL) )
    	descL.setText("Please choose the number of rounds to race.");
    else if( s.equals(norIncB) )
    	descL.setText("Click to increase number of rounds. Maximum is " + norL.getMax() + " rounds.");
    else if( s.equals(norDecB) )
    	descL.setText("Click to decrease number of rounds. Minimun is " + norL.getMin() + " rounds.");
    else if( s.equals(playerL) || s.equals(nopL) )
    	descL.setText("Please choose the number of players!");
    else if( s.equals(nopIncB) )
    	descL.setText("Click to increase number of players. Maximum is " + nopL.getMax() + " players.");
    else if( s.equals(nopDecB) )
    	descL.setText("Click to decrease number of players. Minimun is " + nopL.getMin() + " players.");
    else if( s.equals(playerCustB) )
    	descL.setText("Click to open player-setup.");
    else if( s.equals(profiB) )
    	descL.setText("Click to open professional-setup.");
    else if( s.equals(applyB) )
    	descL.setText("Apply configuration and start new game.");
    else if( s.equals(closeB) )
    	descL.setText("Close window and discard changes.");
    else if( s.equals(tpP) )
    	descL.setText(tpP.getState());
    else if( s.equals(descL) )
    	descL.setText("Move mouse to a component in this window to get description.");
    else
    	descL.setText("Please choose the parameters for the new game!");
  }

  public void initNewGame()
  {
  	// check tempTrack
  	if( (tempTrack.isEmptyTrack()) && (tpP.isRandom()) )
    {
    	tempTrack = RFETrack.getRandomTrack();
    }
    else if( tempTrack.isEmptyTrack() )
    {
    	boolean random = false;

      // fenster

      if( random )
      {
      	tempTrack = RFETrack.getRandomTrack();
      }
      else
      {
      	System.out.println("Cancel");
        return;
      }
    }
    // additional security check
    if( tempTrack.isEmptyTrack() )
    {
    	System.out.println("An error occurred while loading track");
      return;
    }
    // load other settings
    int nor = norL.getValue();
    int nop = nopL.getValue();
    if( nor < 1 || nop < 0 )
    {
    	System.out.println("Illegal number of rounds or players");
      return;
    }
    Car[] c;
    try
    {
      c = Car.initCars(nop, playerNames, playerColors);
    }
    catch(IllegalArgumentException e)
    {
    	System.out.println(e.getMessage());
      return;
    }
    game.startNewGame(tempTrack, nor, nop, c);
    game.getPlayers()[game.getPlayerCounter().getActualPlayer()].calcPossibleMoves();
  }
}