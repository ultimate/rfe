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

public class TrackToolGUI extends JWindow implements RFEConstants, ActionListener
{
  private JLabel 			titleL, descL, roundsL, playerL, custColL, directionL, checksL, timeL, custGridL, gridSizeL, gridTransL, toleranceL, messageL, modeL;
  private IncDecLabel norL, nopL, nocL, secL, grsL, grtL, tolL;
  private JButton 		closeB, applyB;
  private IncDecButton norIncB, norDecB, nopIncB, nopDecB, nocIncB, nocDecB, secIncB, secDecB, grsIncB, grsDecB, grtIncB, grtDecB, tolIncB, tolDecB;
  private JRadioButton[]   custColRB, directionRB, custGridRB, messageRB, modeRB;
  private ButtonGroup custColBG, directionBG, custGridBG, messageBG, modeBG;
  private String[] yesno = {"yes", "no"};
  private String[] directionRound = {"cw", "ccw"};
  private String[] directionSprint = {"fw", "bw"};
  private String[] mode = {"A-A", "A-B"};
  private ColorDefiner[] cd = new ColorDefiner[RFETrack.terrain.length];
  private MessageField	messageF;
  //private Color firstPixel = Color.black;
  //private Color colorCheck = Color.black;

	public TrackToolGUI(JFrame ownerFrame)
  {
  	super(ownerFrame);

    this.setSize(TrackToolGUISize);

    JPanel myContentPane = new JPanel();
  	myContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),BorderFactory.createEtchedBorder()));
    this.setContentPane(myContentPane);
    this.getContentPane().setLayout(null);
  	this.addWindowListener(new TrackToolGUIListener(this));

    titleL = new JLabel("--- Track Tool ---");
    titleL.setBounds(5+border, 5+border, (int)TrackToolGUISize.getWidth()-10-2*border, 25);
    titleL.setHorizontalAlignment(SwingConstants.CENTER);
    titleL.setFont(new Font(titleL.getFont().getName(), Font.PLAIN, 18));

    custColL = new JLabel("Custom Colors:");
    custColL.setBounds(10+border, 40+border, 100, 25);

    custColBG = new ButtonGroup();
    custColRB = new JRadioButton[yesno.length];
    for(int i = 0; i < custColRB.length; i++)
    {
      custColRB[i] = new JRadioButton(yesno[i], i==0);
      custColRB[i].setBounds(110+border+60*i, 40+border, 60, 25);
      custColRB[i].setActionCommand("" + (1-i));
      custColRB[i].addActionListener(new TrackToolGUIListener(this));
      this.getContentPane().add(custColRB[i], null);
      custColBG.add(custColRB[i]);
    }

    toleranceL = new JLabel("Tolerance:");
    toleranceL.setBounds(10+border, 70+border, 110, 25);

    tolL = new IncDecLabel(0,RFETrack.maxTolerance,0);      // evtl zahlen irgendwo herlesen
    tolL.setBounds(125+border, 70+border, 25, 25);
    tolL.setHorizontalAlignment(SwingConstants.RIGHT);

    tolIncB = new IncDecButton('+', tolL);
    tolIncB.setBounds(155+border, 70+border, 20, 12);

    tolDecB = new IncDecButton('-', tolL);
    tolDecB.setBounds(155+border, 82+border, 20, 12);

    modeL = new JLabel("Race-Mode:");
    modeL.setBounds(10+border, 100+border, 100, 25);

    modeBG = new ButtonGroup();
    modeRB = new JRadioButton[mode.length];
    for(int i = 0; i < modeRB.length; i++)
    {
      modeRB[i] = new JRadioButton(mode[i], i==0);
      modeRB[i].setBounds(110+border+60*i, 100+border, 60, 25);
      modeRB[i].setActionCommand("" + i);
      modeRB[i].addActionListener(new TrackToolGUIListener(this));
      this.getContentPane().add(modeRB[i], null);
      modeBG.add(modeRB[i]);
    }

    directionL = new JLabel("Direction:");
    directionL.setBounds(10+border, 130+border, 100, 25);

    directionBG = new ButtonGroup();
    directionRB = new JRadioButton[directionRound.length];
    for(int i = 0; i < directionRB.length; i++)
    {
    	if(modeBG.getSelection().getActionCommand().equals("0"))
      	directionRB[i] = new JRadioButton(directionRound[i], i==0);
			else
      	directionRB[i] = new JRadioButton(directionSprint[i], i==0);
      directionRB[i].setBounds(110+border+60*i, 130+border, 60, 25);
      directionRB[i].setActionCommand("" + i);
//      directionRB[i].addActionListener(new TrackToolGUIListener(this));
      this.getContentPane().add(directionRB[i], null);
      directionBG.add(directionRB[i]);
    }

    roundsL = new JLabel("Rounds:");
    roundsL.setBounds(10+border, 160+border, 110, 25);

    norL = new IncDecLabel(1,RFETrack.maxRounds,1); // evtl zahlen irgendwo herlesen
    norL.setBounds(125+border, 160+border, 25, 25);
    norL.setHorizontalAlignment(SwingConstants.RIGHT);

    norIncB = new IncDecButton('+', norL);
    norIncB.setBounds(155+border, 160+border, 20, 12);

    norDecB = new IncDecButton('-', norL);
    norDecB.setBounds(155+border, 172+border, 20, 12);

    checksL = new JLabel("Check-points:");
    checksL.setBounds(10+border, 190+border, 110, 25);

    nocL = new IncDecLabel(1,RFETrack.maxChecks,1);      // evtl zahlen irgendwo herlesen
    nocL.setBounds(125+border, 190+border, 25, 25);
    nocL.setHorizontalAlignment(SwingConstants.RIGHT);

    nocIncB = new IncDecButton('+', nocL);
    nocIncB.setBounds(155+border, 190+border, 20, 12);

    nocDecB = new IncDecButton('-', nocL);
    nocDecB.setBounds(155+border, 202+border, 20, 12);

    playerL = new JLabel("Players:");
    playerL.setBounds(10+border, 220+border, 110, 25);

    nopL = new IncDecLabel(1,PlayerCounter.getMaxNumberOfPlayers(),2);      // evtl zahlen irgendwo herlesen
    nopL.setBounds(125+border, 220+border, 25, 25);
    nopL.setHorizontalAlignment(SwingConstants.RIGHT);

    nopIncB = new IncDecButton('+', nopL);
    nopIncB.setBounds(155+border, 220+border, 20, 12);

    nopDecB = new IncDecButton('-', nopL);
    nopDecB.setBounds(155+border, 232+border, 20, 12);

    timeL = new JLabel("Time:");
    timeL.setBounds(10+border, 250+border, 110, 25);

    secL = new IncDecLabel(0,RFETrack.maxTime,0,RFETrack.timeFactor);      // evtl zahlen irgendwo herlesen
    secL.setBounds(125+border, 250+border, 25, 25);
    secL.setHorizontalAlignment(SwingConstants.RIGHT);

    secIncB = new IncDecButton('+', secL);
    secIncB.setBounds(155+border, 250+border, 20, 12);

    secDecB = new IncDecButton('-', secL);
    secDecB.setBounds(155+border, 262+border, 20, 12);

    custGridL = new JLabel("Custom Grid:");
    custGridL.setBounds(10+border, 280+border, 100, 25);

    custGridBG = new ButtonGroup();
    custGridRB = new JRadioButton[yesno.length];
    for(int i = 0; i < custGridRB.length; i++)
    {
      custGridRB[i] = new JRadioButton(yesno[i], i==0);
      custGridRB[i].setBounds(110+border+60*i, 280+border, 60, 25);
      custGridRB[i].setActionCommand("" + (1-i));
      custGridRB[i].addActionListener(new TrackToolGUIListener(this));
      this.getContentPane().add(custGridRB[i], null);
      custGridBG.add(custGridRB[i]);
    }

    gridSizeL = new JLabel("Grid Size:");
    gridSizeL.setBounds(10+border, 310+border, 110, 25);

    grsL = new IncDecLabel(5,RFETrack.maxGridSize,20,RFETrack.gridSizeFactor);      // evtl zahlen irgendwo herlesen
    grsL.setBounds(125+border, 310+border, 25, 25);
    grsL.setHorizontalAlignment(SwingConstants.RIGHT);

    grsIncB = new IncDecButton('+', grsL);
    grsIncB.setBounds(155+border, 310+border, 20, 12);

    grsDecB = new IncDecButton('-', grsL);
    grsDecB.setBounds(155+border, 322+border, 20, 12);

    gridTransL = new JLabel("Grid Transparency:");
    gridTransL.setBounds(10+border, 340+border, 110, 25);

    grtL = new IncDecLabel(0,RFETrack.maxGridTrans,0,RFETrack.gridTransFactor);      // evtl zahlen irgendwo herlesen
    grtL.setBounds(125+border, 340+border, 25, 25);
    grtL.setHorizontalAlignment(SwingConstants.RIGHT);

    grtIncB = new IncDecButton('+', grtL);
    grtIncB.setBounds(155+border, 340+border, 20, 12);

    grtDecB = new IncDecButton('-', grtL);
    grtDecB.setBounds(155+border, 352+border, 20, 12);

    messageL = new JLabel("Message:");
    messageL.setBounds(10+border, 370+border, 100, 25);

    messageBG = new ButtonGroup();
    messageRB = new JRadioButton[yesno.length];
    for(int i = 0; i < custGridRB.length; i++)
    {
      messageRB[i] = new JRadioButton(yesno[i], i==1);
      messageRB[i].setBounds(110+border+60*i, 370+border, 60, 25);
      messageRB[i].setActionCommand("" + (1-i));
      messageRB[i].addActionListener(new TrackToolGUIListener(this));
      this.getContentPane().add(messageRB[i], null);
      messageBG.add(messageRB[i]);
    }
		messageRB[0].setVisible(false);

    messageF = new MessageField(RFETrack.maxMessageLength*3);
    messageF.setBounds(5+border, 400, 300, 25);
    messageF.setVisible(!messageBG.getSelection().getActionCommand().equals("0"));

    applyB = new JButton("Apply");
    applyB.setBounds(5+border, 435+border, 170, 25);
    applyB.addActionListener(this);

    descL = new JLabel("---");
    descL.setBounds(5+border, 470+border, (int)TrackToolGUISize.getWidth()-10-2*border+1, 25);
    descL.setBorder(BorderFactory.createEtchedBorder());

    closeB = new JButton();
    closeB.setIcon(new ImageIcon(imageDir + "closeIcon.jpg"));
    closeB.setBounds((int)TrackToolGUISize.getWidth()-2*border-27,border+4,26,25);
    closeB.addActionListener(new TrackToolStartActionListener(this));
    closeB.setMargin(new Insets(-5,-5,-5,-5));

    for(int i = 0; i < cd.length-1; i++)
    {
    	cd[i] = new ColorDefiner(RFETrack.terrain[i+1].getName() + ":");
      cd[i].setBounds(250+border,border+40+30*i,195,25);
      this.getContentPane().add(cd[i], null);
    }
    cd[2].setVisible( !modeBG.getSelection().getActionCommand().equals("0") );
    cd[3].setVisible(  modeBG.getSelection().getActionCommand().equals("0") );
    cd[cd.length-1] = new ColorDefiner("Grid:");
    cd[cd.length-1].setBounds(250+border,border+40+30*(cd.length-1),195,25);
    this.getContentPane().add(cd[cd.length-1], null);

    addMouseMotionListenersToComponents();

    this.getContentPane().add(titleL, null);
    this.getContentPane().add(custColL, null);
    this.getContentPane().add(toleranceL, null);
    this.getContentPane().add(tolL, null);
    this.getContentPane().add(tolIncB, null);
    this.getContentPane().add(tolDecB, null);
    this.getContentPane().add(modeL, null);
    this.getContentPane().add(directionL, null);
    this.getContentPane().add(roundsL, null);
    this.getContentPane().add(norL, null);
    this.getContentPane().add(norIncB, null);
    this.getContentPane().add(norDecB, null);
    this.getContentPane().add(checksL, null);
    this.getContentPane().add(nocL, null);
    this.getContentPane().add(nocIncB, null);
    this.getContentPane().add(nocDecB, null);
    this.getContentPane().add(playerL, null);
    this.getContentPane().add(nopL, null);
    this.getContentPane().add(nopIncB, null);
    this.getContentPane().add(nopDecB, null);
    this.getContentPane().add(timeL, null);
    this.getContentPane().add(secL, null);
    this.getContentPane().add(secIncB, null);
    this.getContentPane().add(secDecB, null);
    this.getContentPane().add(custGridL, null);
    this.getContentPane().add(gridSizeL, null);
    this.getContentPane().add(grsL, null);
    this.getContentPane().add(grsIncB, null);
    this.getContentPane().add(grsDecB, null);
    this.getContentPane().add(gridTransL, null);
    this.getContentPane().add(grtL, null);
    this.getContentPane().add(grtIncB, null);
    this.getContentPane().add(grtDecB, null);
    this.getContentPane().add(messageL, null);
    this.getContentPane().add(messageF, null);
    this.getContentPane().add(applyB, null);
    this.getContentPane().add(closeB, null);
    this.getContentPane().add(descL, null);
  }

  public void addMouseMotionListenersToComponents()
  {
    this.addMouseMotionListener(new TrackToolGUIListener(this));
    roundsL.addMouseMotionListener(new TrackToolGUIListener(this));
    norL.addMouseMotionListener(new TrackToolGUIListener(this));
    norIncB.addMouseMotionListener(new TrackToolGUIListener(this));
    norDecB.addMouseMotionListener(new TrackToolGUIListener(this));
    playerL.addMouseMotionListener(new TrackToolGUIListener(this));
    nopL.addMouseMotionListener(new TrackToolGUIListener(this));
    nopIncB.addMouseMotionListener(new TrackToolGUIListener(this));
    nopDecB.addMouseMotionListener(new TrackToolGUIListener(this));
    applyB.addMouseMotionListener(new TrackToolGUIListener(this));
    closeB.addMouseMotionListener(new TrackToolGUIListener(this));
    descL.addMouseMotionListener(new TrackToolGUIListener(this));
	}

  public void updateDescription(MouseEvent e)
  {
  	Object s = e.getSource();
    if( s.equals(roundsL) || s.equals(norL) )
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
    else if( s.equals(applyB) )
    	descL.setText("Apply configuration and start new game.");
    else if( s.equals(closeB) )
    	descL.setText("Close window and discard changes.");
    else if( s.equals(descL) )
    	descL.setText("Move mouse to a component in this window to get description.");
    else
    	descL.setText("Please choose the parameters for the new game!");
  }

  public void updateColorDefiners(ActionEvent e)
  {
  	if( e.getSource().equals(custColRB[0]) || e.getSource().equals(custColRB[1]) )
    {
	    for(int i = 0; i < cd.length-1; i++)
	    {
	      cd[i].setVisible(e.getActionCommand().equals("1"));
	    }
      toleranceL.setVisible(e.getActionCommand().equals("1"));
      tolL.setVisible(e.getActionCommand().equals("1"));
      tolIncB.setVisible(e.getActionCommand().equals("1"));
      tolDecB.setVisible(e.getActionCommand().equals("1"));
    }
    else if( e.getSource().equals(custGridRB[0]) || e.getSource().equals(custGridRB[1]) )
    {
    	cd[cd.length-1].setVisible(e.getActionCommand().equals("1"));
      gridSizeL.setVisible(e.getActionCommand().equals("1"));
      grsL.setVisible(e.getActionCommand().equals("1"));
      grsIncB.setVisible(e.getActionCommand().equals("1"));
      grsDecB.setVisible(e.getActionCommand().equals("1"));
      gridTransL.setVisible(e.getActionCommand().equals("1"));
      grtL.setVisible(e.getActionCommand().equals("1"));
      grtIncB.setVisible(e.getActionCommand().equals("1"));
      grtDecB.setVisible(e.getActionCommand().equals("1"));
    }
    else if( e.getSource().equals(modeRB[0]) || e.getSource().equals(modeRB[1]) )
    {
    	cd[2].setVisible( !e.getSource().equals(modeRB[0]) );
    	cd[3].setVisible(  e.getSource().equals(modeRB[0]) );
    }
    else if( e.getSource().equals(messageRB[0]) || e.getSource().equals(messageRB[1]) )
    {
    	messageF.setVisible(e.getActionCommand().equals("1"));
    }
  }

  public void updateModeButtons(ActionEvent e)
  {
  	if( e.getSource().equals(modeRB[0]) )
    {
	    for(int i = 0; i < directionRB.length; i++)
	    {
	      directionRB[i].setText( directionRound[i] );
	    }
      setRoundLabelsVisible(true);
    }
    else if( e.getSource().equals(modeRB[1]) )
    {
	    for(int i = 0; i < directionRB.length; i++)
	    {
	      directionRB[i].setText( directionSprint[i] );
	    }
      setRoundLabelsVisible(false);
    }
  }

  public void setRoundLabelsVisible(boolean rounds)
  {
    roundsL.setVisible(rounds);
    norL.setVisible(rounds);
    norIncB.setVisible(rounds);
    norDecB.setVisible(rounds);
  }

  public void actionPerformed(ActionEvent e)
  {
  	calculatePixels();
  }

  public void calculatePixels()
  {
  	String status = "";
    status += custColBG.getSelection().getActionCommand();
    status += modeBG.getSelection().getActionCommand();
    status += directionBG.getSelection().getActionCommand();
	    String nor = "";
      if( modeBG.getSelection().getActionCommand().equals("0") )
      	nor = Integer.toBinaryString(norL.getValue()-1);
	    while(nor.length() < 4)
	      nor = "0" + nor;
    status += nor;
	    String noc = Integer.toBinaryString(nocL.getValue()-1);
	    while(noc.length() < 3)
	      noc = "0" + noc;
    status += noc;
	    String nop = Integer.toBinaryString(nopL.getValue()-1);
	    while(nop.length() < 3)
	      nop = "0" + nop;
    status += nop;
	    String sec = Integer.toBinaryString(secL.getValue()/RFETrack.timeFactor);
	    while(sec.length() < 5)
	      sec = "0" + sec;
    status += sec;
    	String cg = custGridBG.getSelection().getActionCommand();
      String grs = "";
      String grt = "";
      if( cg.equals("1") )
      {
      	grs = Integer.toBinaryString(grsL.getValue()/RFETrack.gridSizeFactor-1);
        grt = Integer.toBinaryString(grtL.getValue()/RFETrack.gridTransFactor);
      }
      while(grs.length() < 4)
        grs = "0" + grs;
      while(grt.length() < 5)
        grt = "0" + grt;
		status += cg + grs + grt;
    status += messageBG.getSelection().getActionCommand();
    status += "011010";
    	String message = messageF.getMessage();
      int ml = RFETrack.maxMessageLength*3;
      if( message.length() < ml )
      	ml = message.length();
      int ml2 = ml/3;
      if(ml%3 != 0)
      	ml2++;
      String messLength = Integer.toBinaryString( ml2 );
      while(messLength.length() < 10)
        messLength = "0" + messLength;
    status += messLength;

    status += "000"; //übrig

    String colCheck = "";
  	//						C					0				1					0
    colCheck += "1100" + "0000" + "0001" + "0000";
	    String tol = Integer.toBinaryString(tolL.getValue());
	    while(tol.length() < 7)
	      tol = "0" + tol;
    colCheck += tol;
    colCheck += "0"; //übrig


    System.out.println(status);
    System.out.println(colCheck);
  }

  public void drawPixels(Graphics g)
  {

  }
}