package Peer;

import Utilities.Bitfield;

public class Peer {
	public String peerID;
	public String hostName;
	public int listeningPort;
	public boolean hasFile;
	public boolean choked;
	public Bitfield bitfield;
	
	public Peer(String peerID, String hostName, int listeningPort, boolean hasFile)
	{
		this.peerID = peerID;
		this.hostName = hostName;
		this.listeningPort = listeningPort;
		this.hasFile = hasFile;
		this.bitfield = null;
		this.choked = false;
	}
	public void setBitfield(Bitfield bitfield)
	{
		this.bitfield = bitfield;
	}
}
