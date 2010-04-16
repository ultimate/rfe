package team.overfed.exp.resources;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import team.overfed.oae.resources.ResourceHandler;
import team.overfed.oae.resources.ResourceSupport;

public class LanguageSwitching extends JFrame implements ResourceSupport, ActionListener, WindowListener
{
	private static final long serialVersionUID = 1L;
	private JButton b;
	private JLabel l1, l2;

	public LanguageSwitching()
	{
		this.setLayout(null);
		this.setSize(400, 300);
		this.addWindowListener(this);

		b = new JButton();
		b.setBounds(50, 50, 300, 50);
		b.addActionListener(this);
		resourceHandler.addObject(b, "setText", String.class, "message");

		l1 = new JLabel();
		l1.setBounds(50, 125, 300, 50);
		resourceHandler.addObject(l1, "setText", String.class, "option.yes");

		l2 = new JLabel();
		l2.setBounds(50, 200, 300, 50);
		resourceHandler.addObject(l2, "setText", String.class, "option.no");

		this.add(b, null);
		this.add(l1, null);
		this.add(l2, null);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(resourceHandler.getLocale().equals(new Locale("de")))
			resourceHandler.setLocale(new Locale("en"));
		else
			resourceHandler.setLocale(new Locale("de"));
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
	}

	public static void main(String[] args)
	{
		ArrayList<ResourceBundle> bundlesDE = new ArrayList<ResourceBundle>();
		bundlesDE.add(ResourceBundle.getBundle("test", new Locale("de")));
		ArrayList<ResourceBundle> bundlesEN = new ArrayList<ResourceBundle>();
		bundlesEN.add(ResourceBundle.getBundle("test", new Locale("en")));

		Map<Locale, ArrayList<ResourceBundle>> localeBundles = new HashMap<Locale, ArrayList<ResourceBundle>>();
		localeBundles.put(new Locale("de"), bundlesDE);
		localeBundles.put(new Locale("en"), bundlesEN);

		ResourceHandler rh = new ResourceHandler(localeBundles, new Locale("de"));
		resourceHandler.replace(rh);

		LanguageSwitching ls = new LanguageSwitching();
		ls.setVisible(true);
		ls.setLocation(200, 200);
	}

}
