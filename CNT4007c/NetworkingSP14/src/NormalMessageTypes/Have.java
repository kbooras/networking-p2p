package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Have extends NormalMessage
{
	private static final long serialVersionUID = 1L;
	
	public Have(byte[] payload, int length, String peerID)
	{
		super(payload, length, peerID);
	}
}
