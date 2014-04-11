import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import MessageTypes.Message;
import NormalMessageTypes.Bitfield;
import Peer.Peer;
import Utilities.Common;

public class TinyTorrent {
	public static Common common;
	public static Bitfield bitfield = null;
	public static ArrayList<Peer> peers = new ArrayList<Peer>();
	public static boolean running = true;
	public static String file1 = "Common.cfg";
	public static String file2 = "PeerInfo.cfg";
	public static String peerID = "1001";
	public static String portNumber = "6008";
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		try{
			peerID = args[0];
			portNumber = args[1];
		}catch(Exception e)
		{
			args = fakeValues();
		}
		
		readCommon();
		readPeerInfo();
		checkValues();
		inputCheck(args);
		
		//Set off client or host thread based on input from command line
	}
	private static String[] fakeValues()
	{
		String[] returnArray = new String[2];
		returnArray[0] = peerID;
		returnArray[1] = portNumber;
		return returnArray;
	}
	private static void inputCheck(String[] args) throws InterruptedException
	{
		if(args.length != 2)
		{
			System.err.println("Invalid Input!");
			System.err.println("Must be executed in the following form:");
			System.err.println("java PeerID PortNumber");
			Thread.sleep(1000);
			System.out.println("Closing in \n5...");
			Thread.sleep(1000);
			System.out.println("4...");
			Thread.sleep(1000);
			System.out.println("3...");
			Thread.sleep(1000);
			System.out.println("2...");
			Thread.sleep(1000);
			System.out.println("1...");
			Thread.sleep(1000);
			System.out.println("Goodbye!");
			Thread.sleep(1000);
			return;
		}
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
//	private static int[] parseBitfieldIndex(Bitfield bitfield)
//	{
//		int[] piecesOfByte = new int[8];
//		
//		int index = 0;
//		
//		int indexValue = bitfield.payload[index];
//		
//		while(indexValue != 0)
//		{
//			int check = 1;
//			int power = 0;
//			
//			while(check <= indexValue)
//			{
//				check *= 2;
//				power++;
//			}
//			
//			if(check != 1)
//			{
//				check /= 2;
//				power--;
//			}
//			
//			
//			piecesOfByte[power] = 1;
//			indexValue -= check;
//		}
//		
//		return piecesOfByte;
//	}
}
