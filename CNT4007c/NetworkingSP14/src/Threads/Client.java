package Threads;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Utilities.Bitfield;
import Utilities.Protocol;
import MessageTypes.HandshakeMessage;
import NormalMessageTypes.BitfieldM;
import Peer.Peer;
import Utilities.Common;

public class Client extends Thread{
	public static ArrayList<Peer> peers;
	public static ArrayList<Peer> neighbors;
	public ArrayList<Thread> threads;
	public Common common;
	public static Bitfield bitfield;
	public String peerID;
	public String port;
	public static boolean running;
	public int hostIndex;

	public Client(Common common, ArrayList<Peer> peers, String peerID, String port)
	{
		this.peerID = peerID;
		this.port = port;
		this.common = common;
		this.peers = peers;
		this.bitfield = null;
		hostIndex = -1;
		setHostIndex();
	}
	public void run()
	{
		threads = new ArrayList<Thread>();
		bitfield.bitfield = setBitfield();
		
		InputStream input = null;
		ObjectInputStream objectInput = null;

		OutputStream output = null;
		ObjectOutputStream objectOutput = null;

		try {			
			Socket socket = new Socket(peers.get(hostIndex).hostName, peers.get(hostIndex).listeningPort);
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
			byte[] hsData = setHandshakeBytes();

			HandshakeMessage hs = new HandshakeMessage(hsData, peerID);
			
			input = socket.getInputStream();
			objectInput = new ObjectInputStream(input);
			
			output = socket.getOutputStream();
			objectOutput = new ObjectOutputStream(output);

			objectOutput.writeObject(hs);
			
			running = true;
			while(running)
			{
				Thread thread = new HandleClient(serverSocket.accept(), bitfield, peerID);
				threads.add(thread);
				thread.run();
			}
			
			closeThreads();
		} catch (Exception e) {
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

			if(objectInput != null)
			{
				try {
					objectInput.close();
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

			if(objectOutput != null)
			{
				try {
					objectOutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private byte[] setHandshakeBytes()
	{
		byte[] hsData = new byte[32];

		hsData[0] = 'H';
		hsData[1] = 'E';
		hsData[2] = 'L';
		hsData[3] = 'L';
		hsData[4] = 'O';

		for(int a = 5; a < 28; a++)
		{
			hsData[a] = '0';
		}

		hsData[28] = (byte) peerID.charAt(0);
		hsData[29] = (byte) peerID.charAt(1);
		hsData[30] = (byte) peerID.charAt(2);
		hsData[31] = (byte) peerID.charAt(3);

		return hsData;
	}
	private void setHostIndex()
	{
		for(int a = 0; a < peers.size(); a++)
		{
			if(peers.get(a).hasFile)
			{
				hostIndex = a;
				break;
			}
		}
	}
	private byte[] setBitfield()
	{
		int numChunks = common.FileSize/common.PieceSize;
		boolean evenDivision = common.FileSize%common.PieceSize == 0;
		
		if(!evenDivision)
		{
			numChunks++;
		}
		
		int numByteIndices = numChunks/8;
		int bitsInRemainder = (numChunks%8);
		boolean evenDivision2 = bitsInRemainder==0;
		
		if(!evenDivision2)
		{
			numByteIndices++; 
		}
		
		byte[] temp = new byte[numByteIndices];
		
		for(int a = 0; a < temp.length; a++)
		{
			temp[a] = (byte) 0;
		}
		
		return temp;
	}
	private void closeThreads()
	{
		for(int a = 0; a < threads.size(); a++)
		{
			threads.get(a).stop();
		}
	}
}