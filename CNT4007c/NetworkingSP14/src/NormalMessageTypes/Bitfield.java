package NormalMessageTypes;

public class Bitfield {
	public int length;
	public int[] payload;
	
	public Bitfield(int length, int[] payload, boolean host)
	{
		this.length = length;
		this.payload = payload;
		
		setBits(host);
	}
	
	private void setBits(boolean host)
	{
		int numIndices = (length/8);
		
		if(length%8 != 0)
		{
			numIndices++;
		}
		
		
	}
	private void setPayload(boolean host, int numIndices)
	{
		int bitValue = 0;
		
		if(host)
		{
			bitValue = 1;
		}
		
		for(int a = 0; a < numIndices; a++)
		{
			payload[a] = bitValue;
		}
	}
	public boolean checkCompletion()
	{
		if(!(length > 0))
		{
			return false;			
		}
		
		for(int a = 0; a < length; a++)
		{
			if(payload[a] == 0)
				return false;
		}
		
		return true;
	}
}
