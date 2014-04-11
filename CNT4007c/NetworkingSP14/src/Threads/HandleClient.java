package Threads;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import MessageTypes.*;
import NormalMessageTypes.*;

public class HandleClient extends Thread{
	Socket socket;
	int portNumber;
	
	public HandleClient(Socket socket)
	{
		this.socket = socket;
	}
	public void run()
	{
		InputStream input = null;
		ObjectInputStream object = null;
		try {
			input = socket.getInputStream();
			object = new ObjectInputStream(input);
			
			Message message = (Message) object.readObject();
			
			if(message.isHandshake)
			{
				//Handle handshake message
			}
			else
			{
				//Handle normal message
				NormalMessage normMessage = new NormalMessage(message.data, message.length, message.peerID);
				
				if(normMessage.type == 0)
				{
					Choke choke = new Choke(normMessage.peerID, normMessage.length);
					
					//Figure out later
				}
				else if(normMessage.type == 1)
				{
					Unchoke unchoke = new Unchoke(normMessage.peerID, normMessage.length);
					
					//Figure out later
				}
				else if(normMessage.type == 2)
				{
					Interested interested = new Interested(normMessage.peerID, normMessage.length);
					
					//Figure out later
				}
				else if(normMessage.type == 3)
				{
					//Peer has said that it is not interested in any piece of the file
					return;
				}
				else if(normMessage.type == 4)
				{
					System.out.println("Error, should not have received any 'Have' messages!");
					System.out.println("Received 'Have' message from peerID: " + normMessage.peerID);
					return;
				}
				else if(normMessage.type == 5)
				{
					System.out.println("Error, should not have received any 'Bitfield' messages!");
					System.out.println("Received 'Bitfield' message from peerID: " + normMessage.peerID);
					return;
				}
				else if(normMessage.type == 6)
				{
					Request request = new Request(normMessage.data, normMessage.length, normMessage.peerID);
					
					//Generate Piece and send back to peerID
				}
				else if(normMessage.type == 7)
				{
					System.out.println("Error, should not have received any 'Piece' messages!");
					System.out.println("Received 'Piece' message from peerID: " + normMessage.peerID);
					return;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(input != null)
			{
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(object != null)
			{
				try {
					object.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(socket != null)
			{
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
