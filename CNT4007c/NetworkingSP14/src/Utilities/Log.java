package Utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Log {
	public static void Status(int receivingID, int sendingID, byte[] payload, String type)
	{
		try {
			Date date = new Date();
			String log = "[" + date.toString() + "]: Peer " + receivingID + 
			" received a '"+type+"' message from " + sendingID + " for the piece " + payload;

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
