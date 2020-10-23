package tris;

public class AvversarioTris
{
	private char segno;
	private char segnoGiocatore;
	private String casellaBloccante = "";
	private GiocoTris game;
	
	/**
	 * Costruttore.
	 * @param s
	 */
	public AvversarioTris(char s, GiocoTris game)
	{
		segno = s;
		if (s=='X') segnoGiocatore = 'O';
		else segnoGiocatore = 'X';
		this.game = game;
	}
	
	/**
	 * Ritorna il segno del computer.
	 * @return char, segno
	 */
	public char getSegno() {return segno;}
	/**
	 * Fa una mossa.
	 * @param scacchiera ScacchieraTris
	 */
	public void turno(ScacchieraTris scacchiera)
	{
		char[][] tabella = scacchiera.getScacchiera();
		int[] mossa = new int[2];
		int result = -2;
		int best = -2;
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++)
			{
				if (tabella[i][j]==' ')
				{
					tabella[i][j] = segno;
					game.addMossa();
					best = minimax(tabella, false);
					if (best>result)
					{
						result = best;
						mossa[0] = i;
						mossa[1] = j;
					}
					tabella[i][j] = ' ';
					game.subMossa();
				}
			}
		scacchiera.occupa(mossa[0], mossa[1], segno);
	}
	
	public void oldTurno(ScacchieraTris scacchiera)
	{
		char[][] tabella = scacchiera.getScacchiera();
		boolean test = false;
		do {
		String mossa = decidi(tabella);
		if (mossa.equals("")) mossa = tattica2(tabella);
		if (mossa.equals(""))
		{
			if (tabella[1][1]==' '&&tattica(tabella))
			{	
				mossa = "11";
			}
			else if (tabella[1][1]==segnoGiocatore&&(tabella[0][0]==' '||tabella[2][2]==' '||tabella[0][2]==' '||tabella[2][0]==' '))
			{
				while (!mossa.equals("00")&&!mossa.equals("22")&&!mossa.equals("02")&&!mossa.equals("20"))
				{
					String[] caselle = scacchiera.caselleLibere();
					int rand = (int)(Math.random()*caselle.length);
					mossa = caselle[rand];
				}
			}
			else
			{
				String[] caselle = scacchiera.caselleLibere();
				int rand = (int)(Math.random()*caselle.length);
				mossa = caselle[rand];
			}
		}
		int h = mossa.charAt(0)-'0';
		int w = mossa.charAt(1)-'0';
		if (tabella[h][w]==' ') test = scacchiera.occupa(h, w, segno);
		} while (!test);
	}
	
	private String decidi(char[][] scacchiera)
	{
		casellaBloccante = "";
		String casella = "";
		casella = decidiO(scacchiera);
		if (!casella.equals("")) return casella;
		casella = decidiV(scacchiera);
		if (!casella.equals("")) return casella;
		casella = decidiD1(scacchiera);
		if (!casella.equals("")) return casella;
		casella = decidiD2(scacchiera);
		if (!casella.equals("")) return casella;
		if (!casellaBloccante.equals("")) return casellaBloccante;
		return "";
	}
	
	private String decidiO(char[][] scacchiera)
	{
		String casella = "";
		for (int h=0;h<3;h++)
		{
			int contG = 0;
			int contC = 0;
			casella = "";
			for (int w=0;w<3;w++)
			{
				if (scacchiera[h][w]==' ') casella = Integer.toString(h)+Integer.toString(w);
				else if (scacchiera[h][w]==segnoGiocatore) contG++;
				else contC++;
			}
			if (!casella.equals(""))
			{
				if (contC==2) return casella;
				else if (contG==2) casellaBloccante = casella;
			}
		}
		return "";
	}
	
	private String decidiV(char[][] scacchiera)
	{
		String casella = "";
		for (int w=0;w<3;w++)
		{
			int contG = 0;
			int contC = 0;
			casella = "";
			for (int h=0;h<3;h++)
			{
				if (scacchiera[h][w]==' ') casella = Integer.toString(h)+Integer.toString(w);
				else if (scacchiera[h][w]==segnoGiocatore) contG++;
				else contC++;
			}
			if (!casella.equals(""))
			{
				if (contC==2) return casella;
				else if (contG==2) casellaBloccante = casella;
			}
		}
		return "";
	}
	
	private String decidiD1(char[][] scacchiera)
	{
		int contG = 0;
		int contC = 0;
		String casella = "";
		if (scacchiera[1][1]==' ') casella = "11";
		else if (scacchiera[1][1]==segnoGiocatore) contG++;
		else contC++;
		if (scacchiera[0][0]==' ') casella = "00";
		else if (scacchiera[0][0]==segnoGiocatore) contG++;
		else contC++;
		if (scacchiera[2][2]==' ') casella = "22";
		else if (scacchiera[2][2]==segnoGiocatore) contG++;
		else contC++;
		if (!casella.equals(""))
		{
			if (contC==2) return casella;
			else if (contG==2) casellaBloccante = casella;
		}
		return "";
	}
	
	private String decidiD2(char[][] scacchiera)
	{
		String casella = "";
		int contG = 0;
		int contC = 0;
		if (scacchiera[1][1]==' ') casella = "11";
		else if (scacchiera[1][1]==segnoGiocatore) contG++;
		else contC++;
		if (scacchiera[2][0]==' ') casella = "20";
		else if (scacchiera[2][0]==segnoGiocatore) contG++;
		else contC++;
		if (scacchiera[0][2]==' ') casella = "02";
		else if (scacchiera[0][2]==segnoGiocatore) contG++;
		else contC++;
		if (!casella.equals(""))
		{
			if (contC==2) return casella;
			else if (contG==2) casellaBloccante = casella;
		}
		return "";
	}
	
	private boolean tattica(char[][] scacchiera)
	{
		if (scacchiera[0][0]==segnoGiocatore||scacchiera[2][2]==segnoGiocatore||scacchiera[0][2]==segnoGiocatore||scacchiera[2][0]==segnoGiocatore) return true;
		return false;
	}
	
	private String tattica2(char[][] scacchiera)
	{
		if (scacchiera[0][1]==segnoGiocatore)
		{
			if (scacchiera[1][0]==segnoGiocatore&&scacchiera[0][0]==' ') return "00";
			else if (scacchiera[1][2]==segnoGiocatore&&scacchiera[0][2]==' ') return "02";
		}
		else if (scacchiera[2][1]==segnoGiocatore)
		{
			if (scacchiera[1][0]==segnoGiocatore&&scacchiera[2][0]==' ') return "20";
			else if (scacchiera[1][2]==segnoGiocatore&&scacchiera[2][2]==' ') return "22";
		}
		return "";
	}
	
	private int minimax(char[][] scacchiera, boolean isMax)
	{
		int win = game.controlloVittoria(scacchiera, segno);
		if (win!=0 || game.mosse==9)
		{
			if (win==1)
				return 1;
			else if (win==2)
				return -1;
			else
				return 0;
		}
		if (isMax)
		{
			int result = -2;
			for (int i=0; i<3; i++)
				for (int j=0; j<3; j++)
				{
					if (scacchiera[i][j]==' ')
					{
						scacchiera[i][j] = segno;
						game.addMossa();
						result = Math.max(result, minimax(scacchiera, false));
						scacchiera[i][j] = ' ';
						game.subMossa();
					}
				}
			return result;
		}
		else
		{
			int result = 2;
			for (int i=0; i<3; i++)
				for (int j=0; j<3; j++)
				{
					if (scacchiera[i][j]==' ')
					{
						scacchiera[i][j] = segnoGiocatore;
						game.addMossa();
						result = Math.min(result, minimax(scacchiera, true));
						scacchiera[i][j] = ' ';
						game.subMossa();
					}
				}
			return result;
		}
	}
}
