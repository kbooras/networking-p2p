package MessageTypes;

import NormalMessageTypes.*;

public class NormalMessage {
		int length;
		int type;
		String payload;
		
		public NormalMessage()
		{
			length = -1;
			type = -1;
			payload = null;
		}
		public NormalMessage(int length, int type, String payload)
		{
			this.length = length;
			this.type = type;
			this.payload = payload;
			
			SetType();
		}

		public int getLength()
		{
			return length;
		}
		public void setLength(int length)
		{
			this.length = length;
		}
		public int getType()
		{
			return type;
		}
		public void setType(int type)
		{
			this.type = type;
		}
		public String getPayload()
		{
			return payload;
		}
		public void setPayload(String payload)
		{
			this.payload = payload;
		}
		
		public void SetType()
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
				
				interested.doSomething();
			}
			else if(type == 3)
			{
				NotInterested notInterested = new NotInterested(length, payload);
				
				notInterested.doSomething();
			}
			else if(type == 4)
			{
				Have have = new Have(length, payload);
				
				have.doSomething();
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
			}

		}
}
