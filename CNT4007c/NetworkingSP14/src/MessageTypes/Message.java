package MessageTypes;
import MessageTypes.*;

public abstract class Message {
	public int peerID;
	public String data;
	
	public Message(String data, int peerID)
	{
		this.data = data;
		this.peerID = peerID;
	}
}
