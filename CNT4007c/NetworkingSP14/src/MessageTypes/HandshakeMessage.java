package MessageTypes;

public class HandshakeMessage extends Message
{
	private static final long serialVersionUID = 1L;
	public HandshakeMessage(byte[] data, String peerID) {
		super(data, 32, peerID, true);
	}
}
