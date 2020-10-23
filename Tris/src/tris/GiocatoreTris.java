package tris;

import java.util.Scanner;

public class GiocatoreTris
{
	private char segno;
	private String nome;
	
	/**
	 * Costruttore.
	 * @param segno String, segno con cui gioca
	 */
	public GiocatoreTris(char segno)
	{
		Scanner s = new Scanner(System.in);
		String n = s.nextLine();
		while (n.indexOf('-')>=0)
		{
			System.out.println("Il nome del giocatore non puo' contenere il carattre '-'\nReinserire il nome");
			n = s.nextLine();
		}
		nome = n;
		this.segno = segno;
	}
	
	/**
	 * Ritorna il nome del giocatore.
	 * @return String, nome
	 */
	public String getNome() {return nome;}
	
	/**
	 * Ritrona il segno con cui sta giocando il giocatore.
	 * @return String
	 */
	public char getSegno() {return segno;}
	
	/**
	 * Fa una mossa.
	 * @param scacchiera ScacchieraTris
	 */
	public void turno(ScacchieraTris scacchiera)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Immettere l'ascissa.");
		int w = s.nextInt()-1;
		System.out.println("Immettere l'ordinata.");
		int h = s.nextInt()-1;
		while (!scacchiera.occupa(h, w, segno))
		{
			System.out.println("La posizione e' già occupata.");
			System.out.println("Immettere l'ascissa.");
			w = s.nextInt()-1;
			System.out.println("Immettere l'ordinata.");
			h = s.nextInt()-1;
		}
	}
}
