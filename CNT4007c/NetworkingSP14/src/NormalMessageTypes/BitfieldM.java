package NormalMessageTypes;

import MessageTypes.NormalMessage;
import Utilities.Bitfield;

public class BitfieldM extends NormalMessage
{
	private static final long serialVersionUID = 1L;
	
	public BitfieldM(Bitfield bitfield, int length, String peerID)
	{
		super(bitfield.bitfield, length, peerID);
	}
}
