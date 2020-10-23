package tris;

import java.util.Arrays;
import java.util.Scanner;

public class Tris
{
	private Scanner s = new Scanner(System.in);
	private String[] statisticheS = new String[0];
	private int vittorieFabrizio;
	private int vittorieNicola;
	private int pareggiCC;
	private String[] statisticheVs = new String[0];
		
	/**
	 * Avvia il programma.
	 */
	public void avvia()
	{
		boolean uscita = false;
		do
		{
			int scelta = menu();
			switch (scelta)
			{
				case 1:
					GiocoTrisSingolo game1 = new GiocoTrisSingolo();
					do
					{
						String vincitore = game1.start();
						setStatistiche(0,vincitore);
						game1.stampaStatistiche();
						System.out.println("Vuoi fare un'altra partita?\nS/N");
					} while (s.nextLine().equalsIgnoreCase("S"));
					break;
				case 2:
					GiocoTrisMultigiocatore game2 = new GiocoTrisMultigiocatore();
					do
					{
						String vincitore = game2.start();
						setStatistiche(1,vincitore);
						game2.stampaStatistiche();
						System.out.println("Volete fare un'altra partita?\nS/N");
					} while (s.nextLine().equalsIgnoreCase("S"));
					break;
				case 3:
					GiocoTrisComputer game3 = new GiocoTrisComputer();
					do
					{
						int vincitore = game3.start();
						switch (vincitore)
						{
							case 0: pareggiCC++; break;
							case 1: vittorieFabrizio++; break;
							case 2: vittorieNicola++; break;
						}
						game3.stampaStatistiche();
						System.out.println("Vuoi assistere ad un'altra partita?\nS/N");
					} while (s.nextLine().equalsIgnoreCase("S"));
					break;
				case 4: 
					do
					{
						stampaStatistiche();
						System.out.println("Vuoi vedere altre statistiche?\nS/N");
					} while (s.nextLine().equalsIgnoreCase("S"));
					break;
				case 5: resetStatistiche(); break;
				case 6: regole(); break;
				case 7: uscita = true; break;
			}
			if (uscita) break;
			System.out.println("Cosa vuoi fare?\n1 = Torna al menu\n2 = Esci");
		} while (s.nextInt()==1);
		s.close();
		System.out.println("Grazie per aver giocato!");
	}
	
	/**
	 * Fa partire il menu di gioco.
	 * @return int, scelta
	 */
	public int menu()
	{
		System.out.println("Benvenuto al giuoco del Tris!\n\nScegli la modalita' di gioco:");
		System.out.println("1 = Giocatore singolo");
		System.out.println("2 = 2 giocatori");
		System.out.println("3 = Computer vs Computer");
		System.out.println("4 = Statistiche");
		System.out.println("5 = Reset statistiche");
		System.out.println("6 = Regole");
		System.out.println("7 = Esci");
		return s.nextInt();
	}
	
	public void regole() {System.out.println("Il gioco consiste nell'inserire in fila tre segni in orizzontale, verticale o diagonale prima dell'avversario.\n");}
	
	/**
	 * Stampa le statistiche del gioco.
	 */
	public void stampaStatistiche()
	{
		System.out.println("Quali statistiche vuoi vedere?");
		System.out.println("1 = Giocatore singolo");
		System.out.println("2 = 2 giocatori");
		System.out.println("3 = Computer vs Computer");
		int scelta = s.nextInt();
		switch (scelta)
		{
			case 1: 
				if (statisticheS.length>0)
				{
					for (int i=0;i<statisticheS.length;i+=4)
					{
						System.out.println("Giocatore: "+statisticheS[i]+" ----- Vittorie: "+statisticheS[i+1]+", Sconfitte: "+statisticheS[i+2]+", Pareggi: "+statisticheS[i+3]);
					}
				}
				else System.out.println("Le statistiche sono vuote.");
				break;
			case 2:
				if (statisticheVs.length>0)
				{
					System.out.println(statisticheS.length);
					for (int i=0;i<statisticheVs.length;i+=4)
					{
						String partita = statisticheVs[i];
						int separazione = partita.indexOf('-');
						String g1 = partita.substring(0, separazione);
						String g2 = partita.substring(separazione+1);
						System.out.println("Partita: "+partita+" ----- "+g1+": "+statisticheVs[i+1]+", "+g2+": "+statisticheVs[i+2]+", Pareggi: "+statisticheVs[i+3]+", ");
					}
				}
				else System.out.println("Le statistiche sono vuote.");
				break;
			case 3: System.out.println("Fabrizio: "+vittorieFabrizio+", Nicola: "+vittorieNicola+" Pareggi: "+pareggiCC); break;
		}
	}
	
	/**
	 * Setta le statistiche per il gioco vs.
	 * @param vincitore String
	 */
	public void setStatistiche(int cambio, String vincitore)
	{
		char controllo = vincitore.charAt(0);
		vincitore = vincitore.substring(1);
		boolean test = true;
		String[] array = new String[7];
		switch (cambio)
		{
			case 0: array = statisticheS; break;
			case 1: array = statisticheVs; break;
		}
		if (array.length!=0)
		{
			for (int i=0;i<array.length;i+=4)
			{
				if (vincitore.equals(array[i]))
				{
					test = false;
					int cont = 0;
					switch (controllo)
					{
						case '0':
							cont = Integer.parseInt(array[i+3]);
							cont++;
							array[i+3] = Integer.toString(cont); break;
						case '1':
							cont = Integer.parseInt(array[i+1]);
							cont++;
							array[i+1] = Integer.toString(cont); break;
						case '2':
							cont = Integer.parseInt(array[i+2]);
							cont++;
							array[i+2] = Integer.toString(cont); break;
					}
					break;
				}
			}
		}
		if (test)
		{
			
			array = Arrays.copyOf(array, array.length+4);
			int lunghezza = array.length;
			array[lunghezza-4] = vincitore;
			switch (controllo)
			{
				case '0':
					array[lunghezza-1] = "1";
					array[lunghezza-2] = "0";
					array[lunghezza-3] = "0"; break;
				case '1':
					array[lunghezza-1] = "0";
					array[lunghezza-2] = "0";
					array[lunghezza-3] = "1"; break;
				case '2':
					array[lunghezza-1] = "0";
					array[lunghezza-2] = "1";
					array[lunghezza-3] = "0"; break;
			}
		}
		switch (cambio)
		{
			case 0: statisticheS = array; break;
			case 1: statisticheVs = array; break;
		}
	}
	
	/**
	 * Resetta le statistiche scelte dall'utente.
	 */
	public void resetStatistiche()
	{
		System.out.println("quali statistiche vuoi resettare?");
		System.out.println("1 = Giocatore singolo");
		System.out.println("2 = 2 giocatori");
		System.out.println("3 = Computer vs Computer");
		System.out.println("4 = Tutte");
		int scelta = s.nextInt();
		switch (scelta)
		{
			case 1: resetSingle(); break;
			case 2: resetMulti(); break;
			case 3: resetComp(); break;
			case 4:
				resetSingle();
				resetMulti();
				resetComp(); break;
		}
	}

	 /**
	  * Resetta le statistiche del singleplayer.
	  */
	private void resetSingle() {statisticheS = new String[0];}
	
	/**
	 * Resetta le statistiche del multiplayer.
	 */
	private void resetMulti() {statisticheVs = new String[0];}

	/**
	 * Resetta le statistiche del computer vs computer.
	 */
	private void resetComp()
	{
		vittorieFabrizio = 0;
		vittorieNicola = 0;
		pareggiCC = 0;
	}
}
