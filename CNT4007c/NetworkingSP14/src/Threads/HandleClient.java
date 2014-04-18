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
		
	}
}
