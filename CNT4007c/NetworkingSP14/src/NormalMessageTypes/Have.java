package NormalMessageTypes;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

import Utilities.Log;


public class Have {
	int length;
	String payload; //4-byte index of file piece. which is byte[] payload = new byte[4];
	
	public Have(int length, String payload)
	{
		this.length = length;
		this.payload = payload;
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
	//what happens when you receive this message
	public void doSomething(int sendingID, int receivingID)
	{
		//if peer A receives a have message from peer B that contains the index of a piece that peer A does not have, 
		//then peer A sends an interested message to peer B
//		if (receivingID.hasPiece(payload)) {
//			NormalMessage newMessage = (length, 2, null); //find out the length, interested has no payload
//			receivingID.sendMessage(newMessage, sendingID);
//		}
//		//else, peer A sends a not interested message to B
//		else if (!receivingID.hasPiece(payload)) {
//			NormalMessage newMessage = (length, 3, null); //find out the length, not interested has no payload
//			receivingID.sendMessage(newMessage, sendingID);
//		}

		//generate log
		Log.Status(receivingID, sendingID, payload, "have");
		
		//peers maintain bitfields to all neighbors and updates them whenever it receives a have message from a neighbor
		//***Not sure how to do this***
		
	}


}
