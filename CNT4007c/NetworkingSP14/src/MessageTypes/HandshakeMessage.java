package MessageTypes;

public class HandshakeMessage extends Message{
	public HandshakeMessage(String data, int peerID) {
		super(data, peerID);
	}
	public static int HandleMessage(String input)
	{

		try{
			int peerID = ParseData(input);
			return peerID;
		}catch(Exception e)
		{
			System.out.println("Warning: Wrong Format For a 'Handshake Message'.");
			return 0;
		}
	}
	private static int ParseData(String input)
	{
		String header = input.substring(0, 5);
		String zeroBits = input.substring(5,28);
		int peerID = Integer.parseInt(input.substring(28, 32));
		
		if(!(headerCheck(header) && zeroBitCheck(zeroBits)))
		{
			return 0;
		}
				
		return peerID;
	}
	private static boolean headerCheck(String header)
	{
		try{
			if(header.equalsIgnoreCase("HELLO"))
				return true;
		}catch(Exception e)
		{
			return false;
		}
		
		return false;
	}
	private static boolean zeroBitCheck(String zeroBits)
	{
		try{
			for(int a = 0; a < zeroBits.length(); a++)
			{
				if(zeroBits.charAt(a) != '0')
				{
					System.out.println("Warning: The ZeroBit portion of Handshake Message has the wrong format.");
					return false;
				}
			}
			
			if(zeroBits.length() != 0)
			{
				return true;
			}
			
			System.out.println("Warning: The ZeroBit portion of Handshake Message is empty.");
		}catch(Exception e)
		{
			return false;
		}
		
		return false;
	}
}
