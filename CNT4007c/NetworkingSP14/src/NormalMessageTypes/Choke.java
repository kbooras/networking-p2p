package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Choke extends NormalMessage 
{	
	private static final long serialVersionUID = 1L;

	public Choke(int peerID, int length)
	{
		super(null, length, peerID);
	}
}
