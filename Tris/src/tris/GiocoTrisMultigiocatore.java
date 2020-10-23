package tris;

public class GiocoTrisMultigiocatore
{
	private GiocatoreTris player1;
	private GiocatoreTris player2;
	private int mosse;
	private ScacchieraTris scacchiera;
	private int vittorieGiocatore1;
	private int vittorieGiocatore2;
	private int pareggi;
	
	/**
	 * Inizia il gioco.
	 */
	public String start()
	{
		mosse = 0;
		scacchiera = new ScacchieraTris();
		scacchiera.stampa();
		System.out.println("Inserire il nome del primo giocatore (X)");
		player1 = new GiocatoreTris('X');
		System.out.println("Inserire il nome del secondo giocatore (O)");
		player2 = new GiocatoreTris('O');
		while (mosse<9&&controlloVittoria(scacchiera.getScacchiera(),player1.getSegno())==0)
		{
			System.out.println("Turno di "+player1.getNome()+"\n");
			player1.turno(scacchiera);
			scacchiera.stampa();
			mosse++;
			if (controlloVittoria(scacchiera.getScacchiera(),player1.getSegno())!=0) break;
			if (mosse==9) break;
			System.out.println("Turno di "+player2.getNome()+"\n");
			player2.turno(scacchiera);
			scacchiera.stampa();
			mosse++;
		}
		String ritorno = player1.getNome()+"-"+player2.getNome();
		switch (controlloVittoria(scacchiera.getScacchiera(),player1.getSegno()))
		{
			case 1:
				vittorieGiocatore1++;
				System.out.println("Ha vinto "+player1.getNome()+"!");
				return "1"+ritorno;
			case 2:
				vittorieGiocatore2++;
				System.out.println("Ha vinto "+player2.getNome()+"!");
				return "2"+ritorno;
			default:
				pareggi++;
				System.out.println("La partita e' finita in pareggio!\n\n\t\t\t\"L'unica mossa vincente e' non giocare.\"");
				return "0"+ritorno;
		}
	}
	
	public void stampaStatistiche() {System.out.println(player1.getNome()+": "+vittorieGiocatore1+", "+player2.getNome()+": "+vittorieGiocatore2+", Pareggi: "+pareggi);}
	
	public int controlloVittoria(char[][] scacchiera, char segno)
	{
		char s = controlloOrizzontale(scacchiera);
		if (s!=' ')
		{
			if (s==segno) return 1;
			else return 2;
		}
		s = controlloVerticale(scacchiera);
		if (s!=' ')
		{
			if (s==segno) return 1;
			else return 2;
		}
		s = controlloDiagonale(scacchiera);
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
	public char controlloOrizzontale(char[][] scacchiera)
	{
		for (int h=0;h<3;h++)
		{
			if (scacchiera[h][0]==scacchiera[h][1]&&scacchiera[h][1]==scacchiera[h][2]) return scacchiera[h][0];
		}
		return ' ';
	}
		
	/**
	 * Controlla se c'e' stato un tris verticale.
	 * 			 
	 * @param scacchiera char[][]
	 * @return char, segno del vincitore
	 */
	public char controlloVerticale(char[][] scacchiera)
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
	public char controlloDiagonale(char[][] scacchiera)
	{
		if (scacchiera[0][0]==scacchiera[1][1]&&scacchiera[1][1]==scacchiera[2][2]) return scacchiera[0][0];
		else if (scacchiera[0][2]==scacchiera[1][1]&&scacchiera[1][1]==scacchiera[2][0]) return scacchiera[0][2];
		return ' ';
	}
}
