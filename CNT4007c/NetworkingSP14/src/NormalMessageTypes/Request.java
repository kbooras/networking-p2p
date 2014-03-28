package NormalMessageTypes;

import MessageTypes.NormalMessage;

public class Request extends NormalMessage{
	public Request(String data, int userID)
	{
		super(data, userID);
	}
}
