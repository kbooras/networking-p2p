package MessageTypes;

import NormalMessageTypes.*;

public class NormalMessage {

	static int length, type;
	static String payload;

	public static boolean HandleMessage(String data, int sendingId, int receivingId)
	{
		ParseData(data);
		
		if((String.valueOf(type).length() + payload.length()) != length)
			return false;
		
		return HandleMessage(sendingId, receivingId);			
	}
	private static boolean HandleMessage(int sendingId, int receivingId)
	{
		if(type == 0)
		{
			Choke choke = new Choke(length, payload);

			choke.doSomething();
		}
		else if(type == 1)
		{
			Unchoke unchoke = new Unchoke(length, payload);

			unchoke.doSomething();
		}
		else if(type == 2)
		{
			Interested interested = new Interested(length, payload);				

			interested.doSomething(sendingId, receivingId);
		}
		else if(type == 3)
		{
			NotInterested notInterested = new NotInterested(length, payload);

			notInterested.doSomething(sendingId, receivingId);
		}
		else if(type == 4)
		{
			Have have = new Have(length, payload);

			have.doSomething(sendingId, receivingId);
		}
		else if(type == 5)
		{
			Bitfield bitfield = new Bitfield(length, payload);

			bitfield.doSomething();
		}
		else if(type == 6)
		{
			Request request = new Request(length, payload);

			request.doSomething();
		}
		else if(type == 7)
		{
			Piece piece = new Piece(length, payload);

			piece.doSomething();
		}
		else
		{
			//Return error if type does not equal one of the 8 enums
			return false;
		}

		return true;
	}
	private static void ParseData(String data)
	{
		length = Integer.parseInt(data.substring(0,4));
		type = Integer.parseInt(data.substring(4,5));
		payload = data.substring(6, data.length()-1);
	}
}
