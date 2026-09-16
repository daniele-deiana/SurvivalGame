import java.util.Random;

public class Evento{
	protected Random random;

	public Evento(){
		random = new Random(); //random(int): genera un numero intero in un range di ampiezza int
	}

	public void esegui(Giocatore giocatore){
		//  metodo pensato per l'override nelle classi figlie
	}
}