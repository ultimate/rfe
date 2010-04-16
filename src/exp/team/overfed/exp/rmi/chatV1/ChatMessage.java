package team.overfed.exp.rmi.chatV1;

import java.io.Serializable;

public class ChatMessage implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String user;
	private String message;

	public ChatMessage(String user, String message)
	{
		super();
		this.user = user;
		this.message = message;
	}

	public String getUser()
	{
		return user;
	}

	public String getMessage()
	{
		return message;
	}
}
