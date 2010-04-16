package team.overfed.exp.rmi.chatV3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import team.overfed.oae.logging.OAELogger;

public class ChatClient extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static OAELogger logger = OAELogger.getOAELogger(ChatServerImpl.class);

	JTextArea output;
	JTextField input;

	ClientHandle handle;
	ChatSession session;
	String nickname;

	public ChatClient(String nickname, String password) throws Exception
	{
		ChatServer server = (ChatServer) Naming.lookup("//192.168.4.24/chat-server");
		handle = new ClientHandleImpl(this);
		session = server.createSession(nickname, password, handle);

		setTitle(nickname);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		output = new JTextArea();
		output.setEditable(false);
		JScrollPane scroller = new JScrollPane();
		scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.getViewport().setView(output);
		getContentPane().add(scroller, BorderLayout.CENTER);
		input = new JTextField();
		getContentPane().add(input, BorderLayout.NORTH);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					sendMessage(input.getText());
					input.setText("");
				}
				catch(RemoteException ex)
				{
					logger.error(ex);
				}
			}
		});

		JButton close = new JButton("close");
		getContentPane().add(close, BorderLayout.SOUTH);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				close();
			}
		});
		setSize(400, 300);
	}

	public void receiveMessage(String time, String nickname, String message)
	{
		output.append(time + nickname + ": " + message + "\n");
		output.setCaretPosition(output.getText().length() - 1);
	}

	public void close()
	{
		System.exit(0);
	}

	public void sendMessage(String message) throws RemoteException
	{
		session.sendMessage(message);
	}

	public static void main(String[] args)
	{
		try
		{
			String user, password;

			String logintitle = "Anmelden...";

			JLabel label = new JLabel("Bitte Benutzerdaten eingeben");
			JLabel tfL = new JLabel("Benutzername:");
			JTextField tf = new JTextField();
			JLabel pwL = new JLabel("Passwort:");
			JPasswordField pw = new JPasswordField();

			while(true)
			{
				tf.setText("");
				pw.setText("");

				int result = JOptionPane.showConfirmDialog(null, new Object[] { label, tfL, tf, pwL, pw }, logintitle, JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION)
				{
					logger.debug("Anmeldung abgebrochen");
					break;
				}

				user = tf.getText();
				password = new String(pw.getPassword());
				logger.debug("Versuche login: \"" + user + "\" ... ");

				try
				{
					ChatClient client = new ChatClient(user, password);
					client.setVisible(true);
					client.requestFocus();
					logger.debug(" Erfolgreich!");
					break;
				}
				catch(Exception e)
				{
					logger.debug(e.getMessage());
					continue;
				}
			}
		}
		catch(Exception ex)
		{
			logger.error(ex);
			System.exit(0);
		}
	}
}