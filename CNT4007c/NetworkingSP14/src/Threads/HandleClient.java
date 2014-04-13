package Threads;

import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import MessageTypes.*;
import NormalMessageTypes.*;
import Peer.Peer;
import Utilities.Bitfield;

public class HandleClient extends Thread
{
	InputStream input = null;
	ObjectInputStream object = null;
	OutputStream output = null;
	ObjectOutputStream outputObject = null;
	Bitfield hostBitfield;
	Socket socket;
	String peerID;
	
	public HandleClient(Socket socket, Bitfield bitfield, String peerID)
	{
		this.socket = socket;
		this.hostBitfield = bitfield;
		this.peerID = peerID;
	}
	public void run()
	{
		try {
			input = socket.getInputStream();
			object = new ObjectInputStream(input);
			
			Message message = (Message) object.readObject();
			
			if(message.isHandshake)
			{
				//If handshake, send bitfield as ack
				BitfieldM bitfieldM = new BitfieldM(hostBitfield, (hostBitfield.size + peerID.length()), peerID);
				
				output = socket.getOutputStream();
				outputObject = new ObjectOutputStream(output);
				
				outputObject.writeObject(bitfieldM);
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
					
					//Generate request for random missing piece (TBD by bitfield comparison)
				}
				else if(normMessage.type == 2)
				{
					Interested interested = new Interested(normMessage.peerID, normMessage.length);
					
					//Figure out later
				}
				else if(normMessage.type == 3)
				{
					//Peer has said that it is not interested in any piece of the file
					//Do something?
					return;
				}
				else if(normMessage.type == 4)
				{
					Have have = new Have(normMessage.data, normMessage.length, normMessage.peerID);
					
					//check to see if you have the piece or not
					//if so, not interested
					//if not, interested
				}
				else if(normMessage.type == 5)
				{
					BitfieldM bitfieldM = new BitfieldM(new Bitfield(normMessage.data), normMessage.length, normMessage.peerID);
					
					//Compare bitfields and send interested/uninterested
				}
				else if(normMessage.type == 6)
				{
					Request request = new Request(normMessage.data, normMessage.length, normMessage.peerID);
					
					//Generate Piece and send back to peerID
				}
				else if(normMessage.type == 7)
				{
					Piece piece = new Piece(normMessage.data, normMessage.length, normMessage.peerID);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(outputObject != null)
			{
				try {
					outputObject.close();
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
			if(input != null)
			{
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(output != null)
			{
				try {
					output.close();
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
