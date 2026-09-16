public class Inventario{
	private String[] oggetti;
	private int numOggetti;

	
	//Inizializza l'inventario con una capienza fissa di 5 elementi.
	public Inventario(){
		this.oggetti = new String[5];
		this.numOggetti = 0;
	}

	public void aggiungi(String oggetto){

		if(this.numOggetti<this.oggetti.length){ 

			this.oggetti[this.numOggetti]=oggetto;
			this.numOggetti++;

		} else {

			System.out.println("Impossibile aggiungere questo oggetto all'inventario: l'inventario è pieno.");

		}

	}

	
	 /*Rimuove l'elemento cercato e comprime l'array spostando gli elementi successivi a sinistra.
	 Questo mantiene la struttura dell'inventario ordinata senza lasciare buchi tra gli oggetti.*/
	public void rimuovi(String oggetto){
		for(int i=0; i<this.numOggetti; i++){ //ciclo che scorre gli elementi dell'array

			if (this.oggetti[i].equals(oggetto)){ //se l'oggetto alla i-esima posizione corrisponde all'oggetto da rimuovere

				for(int j = i; j<this.numOggetti-1; j++){ 

					this.oggetti[j]=oggetti[j+1]; //sposta tutti gli oggetti di una posizione a sinistra

				}

				this.oggetti[this.numOggetti-1] = null; //lo slot più a destra rimane libero
				this.numOggetti--; //il numero degli oggetti diminuisce di uno

				return;

			}

		}

	}

	
	//Visualizza il contenuto dell'inventario oppure un messaggio di vuoto.
	public void mostra(){

		if(this.numOggetti==0){

			System.out.println("Nessun oggetto presente nell'inventario.\n");

		} else{

			for(int i=0; i<this.numOggetti; i++){ //ciclo che scorre l'inventario e ne stampa gli elementi in ordine

				System.out.print(this.oggetti[i] + " | ");

			}
			System.out.println(" ");

		}

	}

	public boolean contiene(String oggetto){

		for(int i=0; i<this.numOggetti; i++){

			if(this.oggetti[i].equals(oggetto)){

				return true;

			}

		}
		return false;

	}
}