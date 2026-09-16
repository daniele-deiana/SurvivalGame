import java.util.NoSuchElementException;

public class Main{
	public static void main(String[] args) {

		if (args.length > 0) {
    		System.out.println("(non sono richiesti argomenti da linea di comando)");
    	}


		Game game = new Game();

		try{

			game.start();

		} catch (NoSuchElementException e){

			System.out.println("\nexit"); //chiusura forzata del programma

		}

	}
}