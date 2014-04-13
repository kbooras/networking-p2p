package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Interested extends NormalMessage
{
	private static final long serialVersionUID = 1L;
	
	public Interested(String peerID, int length)
	{
		super(null, length, peerID);
	}
}
