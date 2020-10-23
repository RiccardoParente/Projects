package tris;

public class GiocoTrisSingolo extends GiocoTris
{
	private GiocatoreTris player;
	private AvversarioTris computer;
	private ScacchieraTris scacchiera;
	private int vittorieGiocatore;
	private int vittorieComputer;
	private int pareggi;
	
	/**
	 * Inizia il gioco.
	 */
	public String start()
	{
		mosse = 0;
		scacchiera = new ScacchieraTris();
		scacchiera.stampa();
		int inizia = (int)(Math.random()*2);
		System.out.println("\nInserire il nome del giocatore.");
		switch (inizia)
		{
			case 0:
				player = new GiocatoreTris('X');
				computer = new AvversarioTris('O', this); break;
			case 1:
				player = new GiocatoreTris('O');
				computer = new AvversarioTris('X', this); break;
		}
		if (player.getSegno()=='X')
		{
			while (mosse<9&&this.controlloVittoria(scacchiera.getScacchiera(),player.getSegno())==0)
			{
				System.out.println("Turno di "+player.getNome()+"\n");
				player.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
				if (this.controlloVittoria(scacchiera.getScacchiera(),player.getSegno())!=0) break;
				if (mosse==9) break;
				System.out.println("Turno del computer\n");
				computer.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
			}
		}
		else
		{
			while (mosse<9&&this.controlloVittoria(scacchiera.getScacchiera(),player.getSegno())==0)
			{
				System.out.println("Turno del computer\n");
				computer.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
				if (this.controlloVittoria(scacchiera.getScacchiera(),player.getSegno())!=0) break;
				if (mosse==9) break;
				System.out.println("Turno di "+player.getNome()+"\n");
				player.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
			}
		}
		String nome = player.getNome();
		switch (this.controlloVittoria(scacchiera.getScacchiera(),player.getSegno()))
		{
			case 1:
				vittorieGiocatore++;
				System.out.println("Ha vinto "+nome+"!");
				return "1"+nome;
			case 2:
				vittorieComputer++;
				System.out.println("Ha vinto il computer!");
				return "2"+nome;
			default:
				pareggi++;
				System.out.println("La partita e' finita in pareggio!\n\n\t\t\t\"L'unica mossa vincente e' non giocare.\"");
				return "0"+nome;
		}
	}
	
	public void stampaStatistiche() {System.out.println(player.getNome()+": "+vittorieGiocatore+", Computer: "+vittorieComputer+", Pareggi: "+pareggi);}
}
