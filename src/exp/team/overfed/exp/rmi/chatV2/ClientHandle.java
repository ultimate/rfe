package team.overfed.exp.rmi.chatV2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandle extends Remote
{

	public void receiveMessage(String time, String nickname, String message) throws RemoteException;

}