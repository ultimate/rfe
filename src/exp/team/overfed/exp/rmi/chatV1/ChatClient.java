package team.overfed.exp.rmi.chatV1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.rmi.Naming;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import team.overfed.oae.logging.OAELogger;

public class ChatClient extends JFrame implements ActionListener, Serializable, WindowListener
{
	private static final long serialVersionUID = 1L;
	private static OAELogger logger = OAELogger.getOAELogger(ChatClient.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		if(args.length < 2)
		{
			logger.error("Bitte Host-Namen und Username eingeben!");
			return;
		}

		ChatClient cc = new ChatClient(args[1]);
		cc.connect(args[0]);
		cc.setVisible(true);

		synchronized(cc)
		{
			cc.wait();
		}

		cc.disconnect();

		System.exit(0);
	}

	// --- Server ---

	private ChatServer server;
	private DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss - ");
	private String user;

	private JTextArea ta;
	private JButton b;
	private JTextField tf;

	public ChatClient(String user)
	{
		this.user = user;

		this.setLayout(null);
		this.setSize(600, 400);
		this.setLocation(100, 100);
		this.addWindowListener(this);

		ta = new JTextArea();
		ta.setBounds(10, 10, 550, 300);
		this.getContentPane().add(ta, null);

		tf = new JTextField();
		tf.setBounds(10, 340, 450, 25);
		this.getContentPane().add(tf, null);

		b = new JButton("Send");
		b.setBounds(470, 340, 80, 25);
		b.addActionListener(this);
		this.getContentPane().add(b, null);
	}

	public void connect(String hostName) throws Exception
	{
		server = (ChatServer) Naming.lookup(hostName);
		server.registerNewClient(this);
	}

	public void disconnect()
	{
		server.unregisterClient(this);
	}

	synchronized public void addMessage(ChatMessage message)
	{
		ta.append(df.format(new Date(System.currentTimeMillis())));
		ta.append(message.getUser() + ":\n");
		ta.append(message.getMessage() + "\n");
		ta.append("-----------------------------------------------------\n");
	}

	@Override
	synchronized public void actionPerformed(ActionEvent arg0)
	{
		ChatMessage cm = new ChatMessage(user, tf.getText());
		server.postMessage(cm);
		tf.setText("");
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		synchronized(this)
		{
			this.notify();
		}
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		synchronized(this)
		{
			this.notify();
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
	}
}
