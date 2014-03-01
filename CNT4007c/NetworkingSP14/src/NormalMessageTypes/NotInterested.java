package NormalMessageTypes;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

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
	public void doSomething()
	{
		//generate log
		try {
			Date date = new Date();
			String log = "[" + date.toString() + "]: Peer " + receivingID + 
			" received a 'not interested' message from " + sendingID + " for the piece " + payload;

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
	}
}
