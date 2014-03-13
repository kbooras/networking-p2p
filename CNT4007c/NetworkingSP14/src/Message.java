import MessageTypes.*;

public class Message {
	static boolean handshakeMsg;
	static boolean normalMsg;
	static int peerID;
	
	public static int VerifyInput(String data, int sendingId, int receivingId)
	{
		peerID = CheckForNormal(data, sendingId, receivingId);
		
		if(!normalMsg)
		{
			peerID = CheckForHandshake(data, sendingId, receivingId);
		}
				
		if(!(handshakeMsg || normalMsg))
		{
			//Exception
			//Not a handshake or normal message, unrecognized message type
			System.err.println("Unrecognized Message Format");
			return 0;
		}
		
		return peerID;
	}
	private static int CheckForHandshake(String data, int sendingId, int receivingId)
	{
		try{
			int peerID = HandshakeMessage.HandleMessage(data);
			handshakeMsg = true;
			return peerID;
		}catch(Exception e)
		{
			System.out.println("Warning: Not a Handshake Message.");
		}
		
		handshakeMsg = false;
		return 0;
	}
	private static int CheckForNormal(String data, int sendingId, int receivingId)
	{
		try{
			normalMsg = NormalMessage.HandleMessage(data, sendingId, receivingId);
			return 1;
		}catch(Exception e)
		{
			System.out.println("Warning: Not a Normal Message");
			normalMsg = false;
			return 0;
		}
	}
}
