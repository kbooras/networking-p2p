package MessageTypes;

public class NormalMessage extends Message {
	public static int length;
	public static int type;
	public static String payload;
	
	public NormalMessage(String data, int peerID) {
		super(data, peerID);
		ParseData(data);
	}

	private static void ParseData(String data)
	{
		length = Integer.parseInt(data.substring(0,4));
		type = Integer.parseInt(data.substring(4,5));
		payload = data.substring(6, data.length()-1);
	}
}
