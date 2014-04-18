package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Request extends NormalMessage
{
	private static final long serialVersionUID = 1L;
	public static int index;
	
	public Request(byte[] payload, int length, String peerID)
	{
		super(payload, length, peerID);
		setIndex(payload);
	}
	private void setIndex(byte[] payload)
	{
		//first four indices (0-3) concatenated?

	}
}
