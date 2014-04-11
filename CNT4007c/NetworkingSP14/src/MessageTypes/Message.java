package MessageTypes;

import java.io.Serializable;

public abstract class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	public int peerID;
	public int length;
	public byte[] data;
	public boolean isHandshake;
	
	public Message(byte[] data, int length, int peerID, boolean isHandshake)
	{
		this.data = data;
		this.length = length;
		this.peerID = peerID;
		this.isHandshake = isHandshake;
	}
}
