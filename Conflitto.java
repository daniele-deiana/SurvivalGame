public class Conflitto extends Evento{

	public Conflitto(){
	}

	/*Il conflitto causa danni al giocatore e può lasciarlo ferito.
	L'uso di munizioni riduce l'impatto del danno e consuma l'oggetto dall'inventario.*/
	@Override
	public void esegui(Giocatore giocatore){

		System.out.println("Ti sei imbattuto in un'orda di infetti!");

		if(giocatore.inventarioContiene("Munizioni")){

			System.out.println("Usi le munizioni presenti nell'inventario per difenderti! Riscontri danni minori.");
			giocatore.subisciDanno(super.random.nextInt(11)+5); //danno casuale tra 5 e 15 hp
			System.out.println("HP: "+giocatore.getHp());
			giocatore.rimuoviDallInventario("Munizioni");

		}else {

			System.out.println("Il conflitto ti provoca dei danni!");
			giocatore.subisciDanno(super.random.nextInt(16)+25); //danno casuale tra 25 e 40 hp
			System.out.println("HP: "+giocatore.getHp());

		}

		if(super.random.nextInt(100)<30){ //probabilità del 30% di rimanere ferito

			System.out.println("Sei rimasto ferito dallo scontro! ");
			giocatore.setFerito(true);

		}
		
	}
}