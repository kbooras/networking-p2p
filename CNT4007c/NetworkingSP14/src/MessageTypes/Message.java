package MessageTypes;

public abstract class Message {
	public String data;
	public int peerID;
	
	public Message(String data, int peerID)
	{
		this.data = data;
		this.peerID = peerID;
	}
}
