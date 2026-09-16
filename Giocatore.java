public class Giocatore{
	private int hp;
	private int hpMax;
	private boolean ferito;
	private Inventario inventario;

	
	//Inizializza il personaggio con HP massimi, inventario vuoto e stato ferito falso
	public Giocatore(){
		this.hp = 100;
		this.hpMax = 100;
		this.inventario = new Inventario();
		this.ferito = false;
	}

	//Cura standard, usata quando ci si imbatte in un rifugio - vedere classe Rifugio metodo esegui
	public void cura(){
		this.hp += 25;

		if (this.hp>this.hpMax){
			this.hp = this.hpMax;
		}
	}

	
	//OVERLOADING: cura usata quando si consuma una benda - vedere classe Game metodo gestisciCura
	public void cura(int cura){
		this.hp += cura;
		if (this.hp>=this.hpMax){
			this.hp = this.hpMax;
		}
	}

	
	//Danno standard, in realtà non viene usato nel gioco ma è utile come esempio
	public void subisciDanno(){
		this.hp -= 25;

		if (this.hp<0){
			this.hp=0;
		}
	}

	//OVERLOADING: Danno parametrico - vedere classe Conflitto metodo esegui oppure classe Game metodo gestisciAvanzamento
	public void subisciDanno(int danno){
		this.hp -= danno;

		if (this.hp<0){
			this.hp=0;
		}
	}


	//Metodi di manipolazione dell'inventario

	public void mostraInventario(){
		this.inventario.mostra();
	}

	public void aggiungiAllInventario(String oggetto){
		this.inventario.aggiungi(oggetto);
	}

	public void rimuoviDallInventario(String oggetto){
		this.inventario.rimuovi(oggetto);
	}

	public boolean inventarioContiene(String oggetto){
		return this.inventario.contiene(oggetto);
	}


	public void setFerito(boolean ferito){
		this.ferito = ferito;
	}


	//getter e setter utili

	public int getHp(){
		return this.hp;
	}
	
	public boolean isFerito(){
		return this.ferito;
	}


	//metodo utile per stampare a schermo i dati del giocatore durante la partita

	public void mostraStato(){

		System.out.println("HP: "+this.hp);
		if(this.ferito){

			System.out.println("Stato: ferito");

		} else {

			System.out.println("Stato: sano");
			
		}

	}

}