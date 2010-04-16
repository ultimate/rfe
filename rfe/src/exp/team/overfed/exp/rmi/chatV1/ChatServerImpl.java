package team.overfed.exp.rmi.chatV1;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import team.overfed.oae.logging.OAELogger;

public class ChatServerImpl implements ChatServer, Serializable
{
	private static final long serialVersionUID = 1L;
	private static OAELogger logger = OAELogger.getOAELogger(ChatServerImpl.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		if(args.length == 0)
		{
			logger.error("Bitte Host-Adresse eingeben!");
			return;
		}

		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		ChatServer cs = new ChatServerImpl();
		((ChatServerImpl) cs).start(args[0]);
		logger.debug("Server started @ " + args[0]);
		logger.debug("-----------------------------------------------------");

		synchronized(cs)
		{
			cs.wait();
		}
	}

	// --- Server ---

	private ArrayList<ChatMessage> messages;
	private ArrayList<ChatClient> clients;
	private DateFormat df = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss - ");

	public ChatServerImpl() throws RemoteException
	{
		super();
		messages = new ArrayList<ChatMessage>();
		clients = new ArrayList<ChatClient>();
	}

	public void start(String hostName) throws RemoteException, MalformedURLException
	{
		Naming.rebind(hostName, this);
	}

	public void postMessage(ChatMessage message)
	{
		messages.add(message);
		printMessageToConsole(message);
		for(ChatClient cc : clients)
			cc.addMessage(message);
	}

	public void registerNewClient(ChatClient cc) throws Exception
	{
		clients.add(cc);
		logger.debug("Client registered! Number of clients now " + clients.size());
		logger.debug("-----------------------------------------------------");
	}

	public void unregisterClient(ChatClient cc)
	{
		clients.remove(cc);
		logger.debug("Client unregistered! Number of clients now " + clients.size());
		logger.debug("-----------------------------------------------------");
		if(clients.size() == 0)
		{
			synchronized(this)
			{
				this.notify();
			}
		}
	}

	private void printMessageToConsole(ChatMessage message)
	{
		logger.debug(df.format(new Date(System.currentTimeMillis())) + " " + message.getUser() + ":");
		logger.debug(message.getMessage());
		logger.debug("-----------------------------------------------------");
	}
}
