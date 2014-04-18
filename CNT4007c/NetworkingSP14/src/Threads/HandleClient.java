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
import Utilities.Protocol;

public class HandleClient extends Thread
{
	ArrayList<Peer> peers;
	ArrayList<Peer> neighbors;
	InputStream input = null;
	ObjectInputStream object = null;
	OutputStream output = null;
	ObjectOutputStream outputObject = null;
	Bitfield bitfield;
	Socket socket;
	String peerID;
	boolean host;
	
	public HandleClient(Socket socket, Bitfield bitfield, String peerID)
	{
		this.socket = socket;
		this.bitfield = bitfield;
		this.peerID = peerID;
		host = true;
	}
	public HandleClient(Socket socket, Bitfield bitfield, ArrayList<Peer> peers, ArrayList<Peer> neighbors, String peerID)
	{
		this.peers = peers;
		this.neighbors = neighbors;
		this.socket = socket;
		this.bitfield = bitfield;
		this.peerID = peerID;
		host = false;
	}
	public void run()
	{
		try{
			if(host)
			{
				handleHost();
			}
			else
			{
				handleClient();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String error = "Initiation of ";
			
			if(host)
			{
				error += "host thread ";
			}
			else
			{
				error += "client thread ";
			}
			
			error += "was unsuccessful!";
			
			System.err.println(error);
		}
	}
	private void handleHost()throws IOException
	{
		while(true)
		{
			new Protocol(socket, bitfield, peerID).handleMessage();
		}
	}
	private void handleClient() throws IOException
	{
		while(true)
		{
			new Protocol(socket, bitfield, peerID, peers, neighbors).handleMessage();
		}
	}
}
