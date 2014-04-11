package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Unchoke extends NormalMessage
{
	private static final long serialVersionUID = 1L;

	public Unchoke(int peerID, int length)
	{
		super(null, length, peerID);
	}
}
