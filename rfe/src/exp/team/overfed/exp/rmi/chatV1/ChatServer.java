package team.overfed.exp.rmi.chatV1;

import java.rmi.Remote;

public interface ChatServer extends Remote
{

	public void postMessage(ChatMessage message);

	public void registerNewClient(ChatClient cc) throws Exception;

	public void unregisterClient(ChatClient cc);
}
