package tris;

import java.util.Arrays;

public class ScacchieraTris
{
	private char[][] scacchiera = new char[3][3];
	
	/**
	 * Costruttore: inizializza una scacchiera da tris vuota.
	 */
	public ScacchieraTris()
	{
		for (int h=0;h<3;h++)
		{
			for (int w=0;w<3;w++)
			{
				scacchiera[h][w] = ' ';
			}
		}
	}
	
	/**
	 * Stampa la scacchiera.
	 */
	public void stampa()
	{
		System.out.println(" 1 2 3");
		for (int h=0;h<3;h++)
		{
			String s = "";
			for (int w=0;w<3;w++)
			{
				s += "|"+scacchiera[h][w];
			}
			s += "| "+(h+1);
			System.out.println(s);
		}
		System.out.println();
	}
	
	/**
	 * Occupa la posizione (w,h) con il segno. Ritorna true se la casella non era precedentemente occupata.
	 * @param h intero, ordinata
	 * @param w intero, ascissa
	 * @param segno String
	 * @return boolean
	 */
	public boolean occupa(int h, int w, char segno)
	{
		if (scacchiera[h][w]==(' ')) {scacchiera[h][w] = segno; return true;}
		else return false;
	}
	
	/**
	 * Ritorna la scacchiera.
	 * @return String[][]
	 */
	public char[][] getScacchiera() {return scacchiera;}
	
	/**
	 * Ritorna tutte le caselle non occupate nella scacchiera.
	 * @return String[]
	 */
	public String[] caselleLibere()
	{
		String[] caselle = new String[9];
		int cont = 0;
		for (int h=0;h<3;h++)
		{
			for (int w=0;w<3;w++)
			{
				String c = "";
				if (scacchiera[h][w]==' ')
				{
					c += String.valueOf(h)+String.valueOf(w);
					caselle[cont] = c;
					cont++;
				}
			}
		}
		caselle = Arrays.copyOf(caselle, cont);
		return caselle;
	}
}
