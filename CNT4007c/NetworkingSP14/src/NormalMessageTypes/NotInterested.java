package NormalMessageTypes;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

import Utilities.Log;

public class NotInterested {
	int length;
	String payload;

	public NotInterested(int length, String payload)
	{
		this.length = length;
		//not interested has no payload
	}

	public int getLength()
	{
		return length;
	}
	public void setLength(int length)
	{
		this.length = length;
	}
	public String getPayload()
	{
		return payload;
	}
	public void setPayload(String payload)
	{
		this.payload = payload;
	}

	//Functionality
	public void doSomething(int sendingID, int receivingID)
	{
		//generate log
		Log.Status(receivingID, sendingID, payload, "notInterested");
	}
}
