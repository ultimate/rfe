package team.overfed.exp.rmi.chatV2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatSession extends Remote
{

	public void sendMessage(String message) throws RemoteException;

}