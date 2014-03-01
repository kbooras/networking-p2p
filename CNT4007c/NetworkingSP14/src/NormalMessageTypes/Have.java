package NormalMessageTypes;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;


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
		if (receivingID.hasPiece(payload)) {
			NormalMessage newMessage = (length, 2, null); //find out the length, interested has no payload
			receivingID.sendMessage(newMessage, sendingID);
		}
		//else, peer A sends a not interested message to B
		else if (!receivingID.hasPiece(payload)) {
			NormalMessage newMessage = (length, 3, null); //find out the length, not interested has no payload
			receivingID.sendMessage(newMessage, sendingID);
		}

		//generate log
		try {
			Date date = new Date();
			String log = "[" + date.toString() + "]: Peer " + receivingID + 
			" received a 'have' message from " + sendingID + " for the piece " + payload;

			String fileName = "log_peer_[" + receivingID + "].log";
			File file = new File(fileName);

			//if file for log does not exist, make it
			if (!file.exists()) {
				file.createNewFile();
			}

			//append data to the file
			FileWriter fileWriter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(log);
			bufferWriter.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//peers maintain bitfields to all neighbors and updates them whenever it receives a have message from a neighbor
		//***Not sure how to do this***
		
	}


}
