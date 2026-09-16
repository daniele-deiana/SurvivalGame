import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class Game{
	private int turno;
	private Giocatore giocatore;
	private Scanner scan; 
	private Random random; 
	private GestoreFile gestoreFile; 

	public Game(){

		this.scan = new Scanner(System.in);
		this.random = new Random();
		this.gestoreFile = new GestoreFile();

	}

	/*Dopo aver stampato l'incipit, comincia un ciclo che potrà venire interrotto solo dal giocatore
	con una scelta nel primo menù o forzando la chiusura del programma con CTRL+C (eccezione gestita nel Main)*/
	public void start(){

		try{

			dots();
			System.out.println(this.gestoreFile.leggiRiga("inizioEfine.txt", 1));
			dots();
			System.out.println(this.gestoreFile.leggiRiga("inizioEfine.txt", 2));
			dots();
			System.out.println(this.gestoreFile.leggiRiga("inizioEfine.txt", 3));
			dots();

		} catch (IOException e){
			System.out.println("Errore durante la lettura dei file: "+ e.getMessage());
		}


		boolean continua = true; //il gioco continua a correre finchè l'utente non decide di uscire (opzione 4)

		while(continua){

			try{

				//Menu iniziale
				System.out.println("\nMENU: "); 
				System.out.println(("1. Riepilogo partita precedente "));
				System.out.println("2. Nuova partita ");
				System.out.println("3. Modalità test: salta direttamente alla vittoria. "); 
				System.out.println("4. Esci - premi 4 adesso oppure CTRL+C in qualsiasi momento del gioco(perderai i dati della partita!)");

				switch(this.scan.nextInt()){

				case 1: //lettura di un file di testo che effettua il riepilogo della scorsa partita

					dots();
					System.out.println("Riepilogo partita precedente: ");
					System.out.println(this.gestoreFile.leggiRiga("riepilogo.txt", 1));
					break;

				case 2: //partita normale, inizia al primo turno

					dots();
					nuovaPartita(1); 
					break;

				case 3: //permette di iniziare la partita dal turno desiderato

					dots();
					System.out.println("Scegli da dove iniziare la tua partita (turno 1 - turno 11): ");
					nuovaPartita(this.scan.nextInt()); //permette di iniziare la partita dal turno desiderato
					break;

				case 4: //pone continua = false; una volta concluso il ciclo attuale il programma raggiunge il termine

					dots();
					System.out.println("Alla prossima partita! ");
					continua = false;
					break;

				default: 

					System.out.println("Opzione non valida: il numero inserito non rientra tra le opzioni disponibili.");
					break;

				}

			} catch (InputMismatchException e ){

				System.out.println("Opzione non valida: è necessario inserire un numero intero! InputMismatchException");
				this.scan.nextLine(); //pulisce il buffer dello Scanner, impedendo che continui a leggere il medesimo carattere cadendo in un loop infinito

			} catch (IOException e){

				System.out.println("Errore durante la lettura dei file: "+ e.getMessage());

			}
		}
	}

	/*Avvia una nuova partita partendo da un turno compreso tra 1 e 10.
	Il ciclo gestisce il progresso, la lettura della tappa corrente e le condizioni di vittoria/sconfitta.*/
	public void nuovaPartita(int turnoDiPartenza){

		if (turnoDiPartenza>0 && turnoDiPartenza <=11){

			this.turno = turnoDiPartenza;
			this.giocatore = new Giocatore(); //vengono inizializzati ora perchè entrano in gioco solo quando viene chiamato il metodo nuovaPartita
		

			try{
				while(this.turno<11 && this.giocatore.getHp()>0){ //ciclo di gioco composto da dieci turni


					System.out.println("\n\n"+this.gestoreFile.leggiRiga("tappe.txt", this.turno)); //ti dice a che punto della storia ti trovi

					eseguiTurno();

					if(this.giocatore.getHp()<=0){ //sconfitta

						System.out.println(this.gestoreFile.leggiRiga("inizioEfine.txt", 4)); //il giocatore muore
						this.gestoreFile.salvaPartita("riepilogo.txt", this.turno);
						return;

					}

					this.turno++;

				}

				if(this.giocatore.getHp()>0){ //vittoria

					System.out.println(this.gestoreFile.leggiRiga("inizioEfine.txt", 5));
					this.gestoreFile.salvaPartita("riepilogo.txt", this.turno);

				}
			} catch (IOException e){
				System.out.println("Errore di lettura o scrittura dei file: "+ e.getMessage());
			}

		} else {

		System.out.println("Il turno scelto non è disponibile: selezionare un numero compreso tra 1(inizio del gioco) e 11(completamento del gioco).");

		}
	}

	/*Gestisce il turno di gioco: il giocatore può curarsi, aprire l'inventario o avanzare.
	Solo dopo tre avanzamenti la partita passa al turno successivo.*/
	public void eseguiTurno(){

		int numeroAvanzamenti = 0; //contatore

		while(numeroAvanzamenti<3 && this.giocatore.getHp()>0){

			System.out.println("\nTurno corrente: "+this.turno);
			this.giocatore.mostraStato();

			System.out.println("\nCosa vuoi fare? ");
			System.out.println("1. Curati\n2. Mostra l'inventario\n3. Avanza");

			
			try{
				
				switch(this.scan.nextInt()){

				case 1: 

					gestisciCura();
					break;

				case 2: 

					System.out.println("\nIl tuo inventario: "); 
					this.giocatore.mostraInventario();
					break;

				case 3: 

					numeroAvanzamenti++;
					dots();
					gestisciAvanzamento();
					dots();
					break;

				default:

					System.out.println("Opzione non valida: il numero inserito non rientra tra le opzioni disponibili. ");
					break;

				}

			} catch (InputMismatchException e ){

			System.out.println("Opzione non valida: è necessario inserire un numero intero! InputMismatchException");
			this.scan.nextLine();

			}

		}
	}

	/*Consuma una benda dall'inventario per recuperare HP.
	Se l'oggetto non è presente, il metodo informa il giocatore.*/
	public void gestisciCura(){

		System.out.println("\nDecidi di curarti. ");

		if(this.giocatore.inventarioContiene("Benda")){

			System.out.println("Ti curi consumando una benda dall'inventario.\n");
			this.giocatore.cura(40); 
			this.giocatore.rimuoviDallInventario("Benda");
			System.out.println("HP: "+giocatore.getHp());

		} else{

			System.out.println("Non sono disponibili cure nell'inventario.\n ");

		}

	}

	//Gestisce l'avanzamento del giocatore, a cui corrisponde un evento casuale.
	public void gestisciAvanzamento(){

		boolean controllo = giocatore.isFerito(); //controllore che evita di applicare il danno due volte nello stesso avanzamento

		System.out.println("\nDecidi di avanzare... ");

		Evento evento = generaEventoCasuale();
		evento.esegui(giocatore);

		if(controllo && this.giocatore.isFerito() && this.giocatore.getHp()>0){ //la ferita viene applicata solo se il giocatore era già ferito

			System.out.println("La tua ferita fa effetto! ");
			this.giocatore.subisciDanno(random.nextInt(8)+8); //in seguito a una ferita si possono perdere tra gli 8 e i 15 hp
			System.out.println("HP: "+giocatore.getHp());

		}

	}

	/*Genera un evento in maniera casuale scegliendo tra conflitto e rifugio.
	La probabilità è calibrata per rendere il gioco più sfidante ma con opportunità di recupero.*/
	public Evento generaEventoCasuale(){

		if(this.random.nextInt(100)<60){
 
			return new Conflitto(); //60% di possibilità di imbattersi in un conflitto

		} else{

			return new Rifugio(); //40% di possibilità di imbattersi in un rifugio

		}

	}

	public void dots(){

		
		try{

			Thread.sleep(100);
			System.out.println(" ");
			System.out.println(" ... ");
			System.out.println(" ");
			Thread.sleep(100);

		} catch (InterruptedException e){

			System.out.println("InterruptedException");

		}

	}
}