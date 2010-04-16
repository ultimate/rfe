package team.overfed.exp.rmi.chatV3;

import java.rmi.Remote;

public interface ChatServer extends Remote
{

	public ChatSession createSession(String nickname, String password, ClientHandle handle) throws Exception;

}