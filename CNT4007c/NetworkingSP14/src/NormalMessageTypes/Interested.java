package NormalMessageTypes;

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
		try {
			Date date = new Date();
			String log = "[" + date.toString() + "]: Peer " + receivingID + 
			" received an 'interested' message from " + sendingID + " for the piece " + payload;

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
