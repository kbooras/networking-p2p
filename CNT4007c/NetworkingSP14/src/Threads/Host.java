package Threads;

import java.util.ArrayList;
import Peer.Peer;
import Utilities.Common;

public class Host extends Thread{
	Common common;
	ArrayList<Peer> peers;
	boolean run;
	
	public Host(Common common, ArrayList<Peer> peers)
	{
		this.common = common;
		this.peers = peers;
	}
	
	public void run()
	{
		run = true;
		while(run)
		{
			//Additional threads will be spun off
			//to handle multiple clients
		}
	}
	public void stopThread()
	{
		run = false;
	}
}
