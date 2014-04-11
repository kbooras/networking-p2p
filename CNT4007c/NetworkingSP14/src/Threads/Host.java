package Threads;

import java.net.ServerSocket;
import java.util.ArrayList;
import Peer.Peer;
import Utilities.Common;

public class Host extends Thread{
	boolean run;
	int portNumber;

	public Host(int portNumber)
	{
		this.portNumber = portNumber;
	}

	public void run()
	{
		try{
			ServerSocket serverSocket = new ServerSocket(portNumber);

			run = true;
			while(run)
			{
				new HandleClient(serverSocket.accept()).run();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void stopThread()
	{
		run = false;
	}
}
