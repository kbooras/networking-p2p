package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class NotInterested extends NormalMessage{
	private static final long serialVersionUID = 1L;

	public NotInterested(String peerID, int length)
	{
		super(null, length, peerID);
	}
}
