package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Bitfield extends NormalMessage{
	public Byte[] bytes;
	
	public Bitfield(String data, int senderID, Byte[] bytes)
	{
		super(data, senderID);
		this.bytes = bytes;
	}
}
