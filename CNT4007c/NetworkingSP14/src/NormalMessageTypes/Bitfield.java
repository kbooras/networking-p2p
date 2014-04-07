package NormalMessageTypes;

public class Bitfield {
	int length;
	int[] payload;
	
	public Bitfield(int length, int[] payload, boolean host)
	{
		this.length = length;
		this.payload = payload;
		
		setBits(host);
	}
	
	private void setBits(boolean host)
	{
		int indices = (length/8);
		
		if(length%8 != 0)
		{
			indices++;
		}
		
		int[] payload = new int[indices];
		
		if(host)
		{
			for(int a = 0; a < indices; a++)
			{
				payload[a] = 1;
			}
		}
		else
		{
			for(int a = 0; a < indices; a++)
			{
				payload[a] = 0;
			}
		}
		
		this.payload = payload;
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
