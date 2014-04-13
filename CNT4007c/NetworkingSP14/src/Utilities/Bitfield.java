package Utilities;

public class Bitfield 
{
	public byte[] bitfield;
	public int size;
	
	public Bitfield(byte[] bitfield)
	{
		this.bitfield = bitfield;
		this.size = bitfield.length;
	}
	public void setBitfield(byte[] bitfield)
	{
		this.bitfield = bitfield;
	}
}
