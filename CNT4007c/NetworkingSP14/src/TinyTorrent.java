import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MessageTypes.Message;
import NormalMessageTypes.Bitfield;
import Utilities.Common;

public class TinyTorrent {
	public static Common common;
	public static Bitfield bitfield = new Bitfield(0, null, false);
	public static ArrayList<Message> messages = new ArrayList<Message>();
	public static ArrayList<Peer> peers = new ArrayList<Peer>();
	public static boolean running = true;
	public static String file1 = "Common.cfg";
	public static String file2 = "PeerInfo.cfg";
	
	public static void main(String[] args) throws IOException
	{		
		readCommon();
		readPeerInfo();
		
		while(running)
		{
			running = checkBitfield(bitfield);
		}
		
		checkValues();
	}
	private static void checkValues()
	{
		System.out.println("====================================");
		System.out.println("Common Info:");
		System.out.println("Number of Preferred Neighbors: "+common.NumberOfPreferredNeighbors);
		System.out.println("Unchoking Interval: "+common.UnchokingInterval);
		System.out.println("Optimistic Unchoking Interval: "+common.OptimisticUnchokingInterval);
		System.out.println("File Name: "+common.FileName);
		System.out.println("File Size: "+common.FileSize);
		System.out.println("Piece Size: "+common.PieceSize);
		System.out.println("------------------------------------");
		System.out.println(peers.size()+" Peers:");
		for(int a = 0; a < peers.size(); a++)
		{
			System.out.println("Peer["+a+"]");
			System.out.println("Peer ID: "+peers.get(a).peerID);
			System.out.println("Host Name: "+peers.get(a).hostName);
			System.out.println("Listening Port: "+peers.get(a).listeningPort);
			System.out.println("Has the file? "+peers.get(a).hasFile);
		}
		System.out.println("====================================");
	}
	private static void readCommon() throws IOException
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file1));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		String line;
		String FileName;
		int NumberOfPreferredNeighbors;
		int UnchokingInterval;
		int OptimisticUnchokingInterval;
		int FileSize;
		int PieceSize;
		
		try{
			line = br.readLine();
			Scanner scanner = new Scanner(line);
			scanner.next();
			NumberOfPreferredNeighbors = Integer.parseInt(scanner.next());
			
			line = br.readLine();
			scanner = new Scanner(line);
			scanner.next();
			UnchokingInterval = Integer.parseInt(scanner.next());
			
			line = br.readLine();
			scanner = new Scanner(line);
			scanner.next();
			OptimisticUnchokingInterval = Integer.parseInt(scanner.next());
			
			line = br.readLine();
			scanner = new Scanner(line);
			scanner.next();
			FileName = scanner.next();
			
			line = br.readLine();
			scanner = new Scanner(line);
			scanner.next();
			FileSize = Integer.parseInt(scanner.next());
			
			line = br.readLine();
			scanner = new Scanner(line);
			scanner.next();
			PieceSize = Integer.parseInt(scanner.next());
			
			common = new Common(NumberOfPreferredNeighbors, UnchokingInterval, OptimisticUnchokingInterval, FileName, FileSize, PieceSize);
		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Could not read "+file1);
		}
		finally
		{
			br.close();
		}
	}
	private static void readPeerInfo() throws IOException
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file2));
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		try{
			int peerID;
			String hostName;
			int listeningPort;
			int fileCheck;
			boolean hasFile;
			
			String line = br.readLine();
			while (line != null) 
			{
				Scanner scanner = new Scanner(line);
				peerID = Integer.parseInt(scanner.next());
				hostName = scanner.next();
				listeningPort = Integer.parseInt(scanner.next());
				fileCheck = Integer.parseInt(scanner.next().trim());
				hasFile = false;
				
				if(fileCheck > 0)
				{
					hasFile = true;
				}

				Peer peer = new Peer(peerID, hostName, listeningPort, hasFile);
				
				peers.add(peer);
				
				line = br.readLine();
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("Could not read "+file2);
		}
		finally
		{
			br.close();
		}
	}
	private static boolean checkBitfield(Bitfield bitfield)
	{
		return bitfield.checkCompletion();
	}
}
