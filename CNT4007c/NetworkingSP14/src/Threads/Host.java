package Threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Date;

import Peer.Peer;
import Utilities.Bitfield;
import Utilities.Common;

public class Host extends Thread
{
	Bitfield bitfield;
	Common common;
	ArrayList<Peer> peers;
	String portNumber;
	String peerID;
	boolean running;

	public Host(Common common, ArrayList<Peer> peers, String peerID, String portNumber)
	{
		this.common = common;
		this.peers = peers;
		this.portNumber = portNumber;
		this.peerID = peerID;
		this.bitfield = new Bitfield(setBitfield());
	}
	//BRING UP: SET BITFIELD
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
		
		for(int a = 0; a < temp.length-1; a++)
		{
			temp[a] = (byte) 255;
		}
		
		if(!evenDivision)
		{			
			temp[temp.length-1] = (byte) (Math.pow(2, bitsInRemainder)-1);
		}
		
		return temp;
	}
	public void run()
	{
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(Integer.parseInt(portNumber));

			running = true;
			while(running)
			{
				new HandleClient(serverSocket.accept(), bitfield, peerID).run();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(serverSocket != null)
			{
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void stopThread()
	{
		running = false;
	}
}
