package MessageTypes;

public class NormalMessage extends Message{
	private static final long serialVersionUID = 1L;
	public int type;

	public NormalMessage(byte[] data, int length, int peerID)
	{
		super(data, length, peerID, false);
		ParseData(data);
	}
	
	
	private static void ParseData(byte[] data)
	{
//		parse type
	}
}
