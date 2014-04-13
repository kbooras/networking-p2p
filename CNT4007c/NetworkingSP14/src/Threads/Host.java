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
		setBitfield();
	}
	//BRING UP: SET BITFIELD
	private void setBitfield()
	{
		int numIndices = common.FileSize/common.PieceSize;
		boolean evenDivision = common.FileSize%common.PieceSize == 0;
		
		if(!evenDivision)
		{
			numIndices++;
		}
		
		byte[] temp = new byte[numIndices];
		
		for(int a = 0; a < temp.length; a++)
		{
			temp[a] = 127;
		}
		
		if(!evenDivision)
		{
			byte remainder = (byte) (common.FileSize%common.PieceSize);
			
			temp[temp.length-1] = remainder;
		}
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
