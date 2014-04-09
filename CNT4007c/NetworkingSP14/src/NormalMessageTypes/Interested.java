package NormalMessageTypes;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

import Utilities.Log;

public class Interested {
	int length;
	String payload;
	
	public Interested(int length, String payload)
	{
		this.length = length;
		//Interested has no payload	
	}
	
	public int getLength()
	{
		return length;
	}
	public void setLength(int length)
	{
		this.length = length;
	}
	
	//Functionality
	//**The peer should send the piece now. No method exists yet for this
	public void doSomething(int sendingID, int receivingID)
	{
		//generate log
		Log.Status(receivingID, sendingID, payload, "interested");
	}
}
