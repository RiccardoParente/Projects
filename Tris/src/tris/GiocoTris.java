package tris;

public abstract class GiocoTris
{
	protected int mosse;
	
	public void addMossa() { mosse++; }
	
	public void subMossa() { mosse--; }
	
	public int controlloVittoria(char[][] scacchiera, char segno)
	{
		char s = this.controlloOrizzontale(scacchiera);
		if (s!=' ')
		{
			if (s==segno) return 1;
			else return 2;
		}
		s = this.controlloVerticale(scacchiera);
		if (s!=' ')
		{
			if (s==segno) return 1;
			else return 2;
		}
		s = this.controlloDiagonale(scacchiera);
		if (s!=' ')
		{
			if (s==segno) return 1;
			else return 2;
		}
		else return 0;
	}
	
	 /**
	  * Controlla se c'e' stato un tris orizzontale.
	  * @param scacchiera char[][]
	  * @return char, segno del vincitore
	  */
	private char controlloOrizzontale(char[][] scacchiera)
	{
		for (int h=0;h<3;h++)
		{
			if (scacchiera[h][0]==scacchiera[h][1]&&scacchiera[h][1]==scacchiera[h][2]) return scacchiera[h][0];
		}
		return ' ';
	}
	
	/**
	 * Controlla se c'e' stato un tris verticale.
	 * @param scacchiera char[][]
	 * @return char, segno del vincitore
	 */
	private char controlloVerticale(char[][] scacchiera)
	{
		for (int w=0;w<3;w++)
		{
			if (scacchiera[0][w]==scacchiera[1][w]&&scacchiera[1][w]==scacchiera[2][w]) return scacchiera[0][w];
		}
		return ' ';
	}
	
	/**
	 * Controlla se c'e' stato un tris diagonale.
	 * @param scacchiera char[][]
	 * @return char, segno del vincitore
	 */
	private char controlloDiagonale(char[][] scacchiera)
	{
		if (scacchiera[0][0]==scacchiera[1][1]&&scacchiera[1][1]==scacchiera[2][2]) return scacchiera[0][0];
		else if (scacchiera[0][2]==scacchiera[1][1]&&scacchiera[1][1]==scacchiera[2][0]) return scacchiera[0][2];
		return ' ';
	}
}
