package NormalMessageTypes;

import MessageTypes.NormalMessage;
import java.util.BitSet;
import java.util.ArrayList;
import java.lang.Math;

public class Bitfield extends NormalMessage
{
	private static final long serialVersionUID = 1L;
	
	public Bitfield(byte[] payload, int length, int peerID)
	{
		super(payload, length, peerID);
	}

	public void updateBitfield(int pieceIndex) 
	{
		BitSet bitset = valueOf(payload[(pieceIndex / 8) -1]);
		bitset.set((pieceIndex % 8) -1);
	}

	//returns index of a random piece that is needed
	public int requestRandomPiece(byte[] serverBitfield) 
	{
		BitSet serverBitset = valueOf(serverBitfield);
		Bitset bitset = valueOf(payload);

		if (serverBitset.length() == bitset.length()) {
			ArrayList missingPieces = new Arraylist();

			for(int i=0; i<serverBitset.length(); i++) {
				if (bitset.get(i) == false) {
					if (serverBitset.get(i) == true) {
						missingPieces.add(i);
					}
				}
			}

			int index = (int) (Math.random() * (missingPieces.size()));

			return missingPieces[index];

		}

		else return -1;
	}

	//return true if you need the piece that the server Has
	public boolean checkForPiece(int pieceIndex) 
	{
		BitSet bitset = valueOf(payload[(pieceIndex / 8) -1]);

		//if you have the piece, return false because you don't need it 
		if (bitset.get((pieceIndex % 8) -1) == true) {
			return false;
		}
		else return true;
	}


	//return true if there are interesting pieces
	public boolean checkForPiece(byte[] serverPayload) 
	{
		BitSet serverBitset = valueOf(serverBitfield);
		Bitset bitset = valueOf(payload);

		if (serverBitset.length() == bitset.length()) {

			for(int i=0; i<serverBitset.length(); i++) {
				if (bitset.get(i) == false) {
					if (serverBitset.get(i) == true) {
						return true;
					}
				}
			}
		}
	}
}
