package NormalMessageTypes;

public class Choke {
	int length;
	String payload;
	
	public Choke(int length, String payload)
	{
		this.length = length;
		this.payload = payload;
	}
	
	public int getLength()
	{
		return length;
	}
	public void setLength(int length)
	{
		this.length = length;
	}
	public String getPayload()
	{
		return payload;
	}
	public void setPayload(String payload)
	{
		this.payload = payload;
	}
	
	//Functionality
	public void doSomething()
	{
		
	}
}
