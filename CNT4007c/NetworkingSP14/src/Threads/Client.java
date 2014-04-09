package Threads;

import java.util.ArrayList;

import Peer.Peer;
import Utilities.Common;

public class Client extends Thread{
	Common common;
	ArrayList<Peer> peers;
	
	public Client(Common common, ArrayList<Peer> peers)
	{
		this.common = common;
		this.peers = peers;
	}
	
	public void run()
	{
		
	}
}