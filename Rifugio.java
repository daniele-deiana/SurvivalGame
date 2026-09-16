public class Rifugio extends Evento{

	public Rifugio(){
	}

	//Il rifugio cura il giocatore e assegna una benda o delle munizioni in maniera casuale all'inventario.
	@Override
	public void esegui(Giocatore giocatore){

		System.out.println("Che fortuna! Ti sei imbattuto in quello che sembra essere un rifugio sicuro, dove potrai riposare e trovare nuove risorse!");

		if (giocatore.isFerito()){

			System.out.println("La tua ferita viene curata!");
			giocatore.setFerito(false);
			System.out.println("HP: "+giocatore.getHp());

		} else {

			System.out.println("Vieni curato!");
			giocatore.cura();
			System.out.println("HP: "+giocatore.getHp());
			
		}

		switch (super.random.nextInt(2)+1){ //munizioni e bende hanno pari possibilità di essere trovate

		case 1: 

			System.out.println("Hai trovato una benda!");
			giocatore.aggiungiAllInventario("Benda");
			break;

		case 2: 

			System.out.println("Hai trovato delle munizioni!");
			giocatore.aggiungiAllInventario("Munizioni");
			break;
			
		}

	}
}