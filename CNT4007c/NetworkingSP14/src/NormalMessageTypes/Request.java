package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Request extends NormalMessage
{
	private static final long serialVersionUID = 1L;

	public Request(byte[] payload, int length, int peerID)
	{
		super(payload, length, peerID);
	}
}
