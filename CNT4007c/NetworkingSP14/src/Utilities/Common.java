package Utilities;

public class Common {
	public int NumberOfPreferredNeighbors;
	public int UnchokingInterval;
	public int OptimisticUnchokingInterval;
	public int FileSize;
	public int PieceSize;
	public String FileName;
	
	public Common(int NumberOfPreferredNeighbors, int UnchokingInterval, int OptimisticUnchokingInterval, String FileName, int FileSize, int PieceSize)
	{
		this.NumberOfPreferredNeighbors = NumberOfPreferredNeighbors;
		this.UnchokingInterval = UnchokingInterval;
		this.OptimisticUnchokingInterval = OptimisticUnchokingInterval;
		this.FileName = FileName;
		this.FileSize = FileSize;
		this.PieceSize = PieceSize;
	}
}
