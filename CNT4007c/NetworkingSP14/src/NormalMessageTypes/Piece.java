package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Piece extends NormalMessage
{
	private static final long serialVersionUID = 1L;

	public Piece(byte[] payload, int length, String peerID)
	{
		super(payload, length, peerID);
	}
}
