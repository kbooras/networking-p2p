package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import MessageTypes.Message;
import MessageTypes.NormalMessage;
import NormalMessageTypes.BitfieldM;
import NormalMessageTypes.Choke;
import NormalMessageTypes.Have;
import NormalMessageTypes.Interested;
import NormalMessageTypes.NotInterested;
import NormalMessageTypes.Piece;
import NormalMessageTypes.Request;
import NormalMessageTypes.Unchoke;
import Peer.Peer;

public class Protocol {
	ArrayList<Peer> neighbors;
	static ArrayList<Peer> peers;
	InputStream input = null;
	ObjectInputStream object = null;
	OutputStream output = null;
	ObjectOutputStream outputObject = null;
	Bitfield hostBitfield;
	Socket socket;
	String peerID;
	boolean host;

	public Protocol(Socket socket, Bitfield hostBitfield, String peerID)
	{
		this.socket = socket;
		this.hostBitfield = hostBitfield;
		this.peerID = peerID;
		host = true;
	}
	public Protocol(Socket socket, Bitfield hostBitfield, String peerID, ArrayList<Peer> neighbors, ArrayList<Peer> peers)
	{
		this.neighbors = neighbors;
		this.socket = socket;
		this.hostBitfield = hostBitfield;
		this.peerID = peerID;
		host = false;
	}

	public void handleMessage()
	{
		if(host)
		{
			handleHost();
		}
		else
		{
			handleClient();
		}
	}
	private void handleHost()
	{
		try {
			input = socket.getInputStream();
			object = new ObjectInputStream(input);

			Message message = (Message) object.readObject();
			
			if(message == null)
			{
				return;
			}

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

				switch(normMessage.type)
				{
				case 0: return;
				case 1: return;
				case 2: return;
				case 3: return;
				case 4: return;
				case 5: return;
				case 6: Request request = new Request(normMessage.data, normMessage.length, normMessage.peerID);

				//use request.index to get the right piece. Generate byte[] for data

				//Generate piece, return to sender
				Piece piece = new Piece(new byte[1], peerID.length()+( new byte[1].length), peerID );

				outputObject.writeObject(piece);
				return;
				case 7: return;
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
	private void handleClient()
	{
		try {
			input = socket.getInputStream();
			object = new ObjectInputStream(input);

			Message message = (Message) object.readObject();

			if(message == null)
			{
				return;
			}
			
			if(message.isHandshake)
			{

				//WILL CHANGE (RESPONSE SHOULD ADD TO NEIGHBORS
				//CHOKE/UNCHOKE?
				//INTERESTED/NI?

				//				//If handshake, send bitfield as ack
				//				BitfieldM bitfieldM = new BitfieldM(hostBitfield, (hostBitfield.size + peerID.length()), peerID);
				//				
				//				output = socket.getOutputStream();
				//				outputObject = new ObjectOutputStream(output);
				//				
				//				outputObject.writeObject(bitfieldM);
			}
			else
			{
				//Handle normal message
				NormalMessage normMessage = new NormalMessage(message.data, message.length, message.peerID);

				switch(normMessage.type)
				{
					case 0: 
						Choke choke = new Choke(normMessage.peerID, normMessage.length);
						
						return;
					case 1: 
						Unchoke unchoke = new Unchoke(normMessage.peerID, normMessage.length);
	
						for(int a = 0; a < neighbors.size(); a++)
						{
							if(neighbors.get(a).peerID == unchoke.peerID)
							{
								neighbors.get(a).choked = false;
							}
						}
						//compare your bitfield to this peer's bitfield
						//add indexes of pieces they have that you do not have to an array
						//randomly select an index of this array and send a request message for that piece
						return;
					case 2: 
						Interested interested = new Interested(normMessage.peerID, normMessage.length);
						
						int indexOfPeer = -1;
						
						for(int a = 0; a < peers.size(); a++)
						{
							if(peers.get(a).peerID.equals(interested.peerID))
							{
								indexOfPeer = a;
							}
						}
						
						if(indexOfPeer != -1)
						{
							neighbors.add(peers.get(indexOfPeer));
						}
						
						return;
					case 3: 
						NotInterested notInterested = new NotInterested(normMessage.peerID, normMessage.length);
						//remove from neighbors
						return;
					case 4: 
						Have have = new Have(normMessage.data, normMessage.length, normMessage.peerID);
						//data is going to be the index of the piece. Go through bitfield and see if 
						//you already have that piece.
						//Send interested/not interested appropriately
		
						return;
					case 5: 
						Bitfield bitfieldInput  = new Bitfield(normMessage.data);
	
						BitfieldM bitfieldM = new BitfieldM(bitfieldInput, normMessage.length, normMessage.peerID); 
	
						//compare own bitfield to this bitfield
						//..if you  need anything
						//send  interested message
						//..else
						//send not interested
						return;
					case 6: 
						Request request = new Request(normMessage.data, normMessage.length, normMessage.peerID);
	
						//use request.index to get the right piece. Generate byte[] for data
		
						//Generate piece, return to sender
						Piece piece = new Piece(new byte[1], peerID.length()+( new byte[1].length), peerID );
		
						outputObject.writeObject(piece);
						return;
					case 7: 
						Piece pieceReceived = new Piece(normMessage.data, normMessage.length, normMessage.peerID); 
	
						//Add piece, write to disk
		
						//Update Bitfield
		
						//Send Have Message to all peers
		
						//Request random piece back from neighbor that just sent prior piece
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
