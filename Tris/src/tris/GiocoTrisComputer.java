package tris;

public class GiocoTrisComputer extends GiocoTris
{
	private AvversarioTris fabrizio;
	private AvversarioTris nicola;
	private ScacchieraTris scacchiera;
	private int vittorieNicola;
	private int vittorieFabrizio;
	private int pareggi;
	
	public int start()
	{
		mosse = 0;
		scacchiera = new ScacchieraTris();
		int inizia = (int)(Math.random()*2);
		switch (inizia)
		{
			case 0:
				fabrizio = new AvversarioTris('X', this);
				nicola = new AvversarioTris('O', this); break;
			case 1:
				fabrizio= new AvversarioTris('O',this );
				nicola = new AvversarioTris('X', this); break;
		}
		if (fabrizio.getSegno()=='X')
		{
			while (mosse<9&&controlloVittoria(scacchiera.getScacchiera(),fabrizio.getSegno())==0)
			{
				System.out.println("Turno di Fabrizio\n");
				fabrizio.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
				if (controlloVittoria(scacchiera.getScacchiera(),fabrizio.getSegno())!=0) break;
				if (mosse==9) break;
				System.out.println("Turno di Nicola\n");
				nicola.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
			}
		}
		else
		{
			while (mosse<9&&controlloVittoria(scacchiera.getScacchiera(),fabrizio.getSegno())==0)
			{
				System.out.println("Turno di Nicola\n");
				nicola.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
				if (controlloVittoria(scacchiera.getScacchiera(),fabrizio.getSegno())!=0) break;
				if (mosse==9) break;
				System.out.println("Turno di Fabrizio\n");
				fabrizio.turno(scacchiera);
				scacchiera.stampa();
				mosse++;
			}
		}
		switch (controlloVittoria(scacchiera.getScacchiera(),fabrizio.getSegno()))
		{
			case 1:
				vittorieFabrizio++;
				System.out.println("Ha vinto Fabrizio!");
				return 1;
			case 2:
				vittorieNicola++;
				System.out.println("Ha vinto Nicola!");
				return 2;
			default:
				pareggi++;
				System.out.println("La partita e' finita in pareggio!\n\n\t\t\t\"L'unica mossa vincente e' non giocare.\"");
				return 0;
		}
	}
	/**
	 * Stampa le statistiche.
	 */
	public void stampaStatistiche() {System.out.println("Fabrizio: "+vittorieFabrizio+", Nicola: "+vittorieNicola+", Pareggi: "+pareggi);}
}
