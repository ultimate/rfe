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

public class ProfiSetupGUI extends JWindow implements RFEConstants
{
  private JButton 		okB, closeB;
  private JLabel			descL, titleL;

	public ProfiSetupGUI(JFrame ownerFrame)
  {
  	super(ownerFrame);

    this.setSize(ProfiSetupGUISize);

    JPanel myContentPane = new JPanel();
  	myContentPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED),BorderFactory.createEtchedBorder()));
    this.setContentPane(myContentPane);
    this.getContentPane().setLayout(null);
    this.addWindowListener(new ProfiSetupGUIListener(this));

    titleL = new JLabel("--- Professional Setup ---");
    titleL.setBounds(5+border, 5+border, (int)ProfiSetupGUISize.getWidth()-10-2*border, 25);
    titleL.setHorizontalAlignment(SwingConstants.CENTER);
    titleL.setFont(new Font(titleL.getFont().getName(), Font.PLAIN, 18));

    okB = new JButton("OK");
    okB.addActionListener(new ApplyProfiSetupActionListener(this));
    okB.setBounds(5+border, border+40, (int)this.getWidth()-10-2*border+1, 25);

    descL = new JLabel("This feature will come in a future-version!");
    descL.setBorder(BorderFactory.createEtchedBorder());
    descL.setBounds(5+border, border+40+40, (int)this.getWidth()-10-2*border+1, 25);

    closeB = new JButton("");
    closeB.setIcon(new ImageIcon(imageDir + "closeIcon.jpg"));
    closeB.setBounds((int)this.getWidth()-2*border-27,border+4,26,25);
    closeB.addActionListener(new ApplyProfiSetupActionListener(this));
    closeB.setMargin(new Insets(-5,-5,-5,-5));

    addMouseMotionListenersToComponents();

    this.getContentPane().add(titleL, null);
    this.getContentPane().add(okB, null);
    this.getContentPane().add(closeB, null);
    this.getContentPane().add(descL, null);
  }

  public void addMouseMotionListenersToComponents()
  {
    this.addMouseMotionListener(new ProfiSetupGUIListener(this));
    okB.addMouseMotionListener(new ProfiSetupGUIListener(this));
    closeB.addMouseMotionListener(new ProfiSetupGUIListener(this));
    descL.addMouseMotionListener(new ProfiSetupGUIListener(this));
	}

  public void updateDescription(MouseEvent e)
  {
  	Object s = e.getSource();
		if( s.equals(okB) )
    	descL.setText("Close window.");
    else if( s.equals(closeB) )
    	descL.setText("Close window and discard changes.");
    else if( s.equals(descL) )
    	descL.setText("Move mouse to a component in this window to get description.");
    else
    	descL.setText("This feature will come in a future-version!");
  }
}