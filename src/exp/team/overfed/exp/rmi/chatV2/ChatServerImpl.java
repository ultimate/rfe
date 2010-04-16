package team.overfed.exp.rmi.chatV2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import team.overfed.oae.logging.OAELogger;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer
{
	private static final long serialVersionUID = 1L;
	private static OAELogger logger = OAELogger.getOAELogger(ChatServerImpl.class);

	List<ChatSession> sessions = new ArrayList<ChatSession>();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss - ");

	public ChatServerImpl() throws RemoteException
	{
	}

	public ChatSession createSession(String nickname, ClientHandle handle) throws RemoteException
	{
		logger.debug("create session: " + nickname);
		ChatSession s = new ChatSessionImpl(this, nickname, handle);
		sessions.add(s);
		logger.debug("current number of sessions: " + sessions.size());
		return s;
	}

	public void postMessage(String message, ChatSessionImpl s)
	{
		String time = df.format(new Date(System.currentTimeMillis()));
		logger.debug(time + s.getNickname() + ": " + message);
		ChatSessionImpl tmp;
		for(int i = 0; i < sessions.size(); i++)
		{
			tmp = (ChatSessionImpl) sessions.get(i);
			try
			{
				tmp.getClientHandle().receiveMessage(time, s.getNickname(), message);
			}
			catch(RemoteException ex)
			{
				logger.debug("unabled to contact client " + s.getNickname());
				logger.debug("removing.");
				removeSession(tmp);
				i--; // Da nun alle Clients in Liste einen Platz nach unten rutschen ...
			}
		}
	}

	public void removeSession(ChatSession session)
	{
		logger.debug("remove session: " + ((ChatSessionImpl) session).getNickname());
		sessions.remove(session);
		logger.debug("current number of sessions: " + sessions.size());
	}

	public static void main(String args[])
	{
		try
		{
			LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Naming.rebind("//192.168.4.24/chat-server", new ChatServerImpl());
			logger.debug("//192.168.4.24/chat-server started!");
		}
		catch(Exception ex)
		{
			logger.error(ex);
		}
	}
}