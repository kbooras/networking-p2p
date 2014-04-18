package Threads;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import Peer.Peer;

public class UpdateBitfields extends Thread
{
	public static ArrayList<Peer> peers;
	public static Socket socket;
	private boolean running;
	
	public UpdateBitfields(ArrayList<Peer> peers, Socket socket)
	{
		UpdateBitfields.peers = peers;
		UpdateBitfields.socket = socket;
	}
	public void run()
	{
		running = true;
		while(running)
		{
			for(int a = 0; a < peers.size(); a++)
			{
				OutputStream output = null;
				ObjectOutputStream object = null;
				
				try {
					output = socket.getOutputStream();
					object = new ObjectOutputStream(output);
					
					//Request bitfield object
					//Wait and listen
					//update bitfield for this user
					//iterate to next peer
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				finally
				{
					if(output != null)
						try {
							output.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					
					if(object != null)
						try {
							object.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		}
	}
}
