package BitTorrent;

import java.util.ArrayList;
import MessageTypes.HandshakeMessage;
import MessageTypes.Message;
import MessageTypes.NormalMessage;
import NormalMessageTypes.Bitfield;
import NormalMessageTypes.Choke;
import NormalMessageTypes.Have;
import NormalMessageTypes.Interested;
import NormalMessageTypes.NotInterested;
import NormalMessageTypes.Piece;
import NormalMessageTypes.Request;
import NormalMessageTypes.Unchoke;

public class BitTorrent {
	static ArrayList<Message> messages;
	static ArrayList<Integer> neighbors;
	static Bitfield bitfield;
	
	public static void main(String[] args)
	{
		messages = new ArrayList<Message>();
		boolean fileIsDone = false;
		
		while(!fileIsDone)
		{
			if(!messages.isEmpty())
			{
				HandleMessages();
			}
			
			
			
			fileIsDone = bitfieldCompletionCheck();
		}
	}
	
	public static boolean bitfieldCompletionCheck()
	{
		for(int a = 0; a < bitfield.bytes.length; a++)
		{
			if(bitfield.bytes[a] != 1)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static void HandleMessages()
	{
			VerifyInput(messages.get(0));	
			
			//Other message handling
	}
	
	public static void VerifyInput(Message message)
	{
		NormalMessage nm = null;
		
		if(CheckForHandshake(message.data, message.peerID) == 0)
		{
			nm = CheckForNormal(message);
		}
		else
		{
			HandleHandshake(message);
			return;
		}
		
		if(nm == null)
		{
			messages.remove(0);
			return;
		}
		
		
	}
	private static int CheckForHandshake(String data, int sendingId)
	{
		try{
			int peerID = HandshakeMessage.HandleMessage(data);
			return peerID;
		}catch(Exception e)
		{
			System.out.println("Warning: Not a Handshake Message.");
		}
		
		return 0;
	}
	private static NormalMessage CheckForNormal(Message message)
	{
		try{
			NormalMessage normalMsg = HandleNormalMessage(message);
			return normalMsg;
		}catch(Exception e)
		{
			System.out.println("Warning: Not a Normal Message");
			return null;
		}
	}

	public static NormalMessage HandleNormalMessage(Message message)
	{
		NormalMessage nm = new NormalMessage(message.data, message.peerID);
		
		if((String.valueOf(nm.type).length() + nm.payload.length()) != nm.length)
			return null;
		
		return HandleMessageType(nm);			
	}
	private static NormalMessage HandleMessageType(NormalMessage nm)
	{
		if(nm.type == 0)
		{
			return new Choke(sendingID);
		}
		else if(nm.type == 1)
		{
			return new Unchoke(length, payload);
		}
		else if(nm.type == 2)
		{
			return new Interested(length, payload);
		}
		else if(nm.type == 3)
		{
			return new NotInterested(length, payload);
		}
		else if(nm.type == 4)
		{
			return new Have(length, payload);
		}
		else if(nm.type == 5)
		{
			return new Bitfield(length, payload);
		}
		else if(nm.type == 6)
		{
			return new Request(length, payload);
		}
		else if(nm.type == 7)
		{
			return new Piece(length, payload);
		}
		else
		{
			//Return error if type does not equal one of the 8 enums
			return null;
		}
	}
}
