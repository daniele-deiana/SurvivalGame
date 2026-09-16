import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestoreFile {

    public GestoreFile(){}

    /*Legge una riga specifica di un file e la restituisce formattata come testo leggibile.
    Il separatore ';' viene usato per suddividere i blocchi informativi della stessa riga.*/
    public String leggiRiga(String percorsoFile, int numeroRiga) throws IOException{

        StringBuilder testo = new StringBuilder();

        try(BufferedReader br = new BufferedReader(new FileReader(percorsoFile))){

            String riga;
            int numRigaCorrente = 0; //contrllore

            while((riga = br.readLine()) != null){ //non considerare le righe vuote

                numRigaCorrente++;

                if(numRigaCorrente == numeroRiga){ //fermati quando arrivi alla riga richiesta

                    if(riga.trim().isEmpty()){ //se la riga è vuota prosegui

                        continue;

                    }

                    String[] parti = riga.split(";"); //dividi la riga quando incontri ";", creando un array

                    for(int i=0; i<parti.length; i++){ //ciclo che aggiunge allo Stringbuilder le parti di testo lette andando a capo dopo il ";"

                        testo.append(parti[i].trim()).append("\n");

                    }

                    return testo.toString();

                }

            }

        }

        return ("Errore, riga richiesta non presente nel testo.");

    }

    /*Restituisce la prima parola di una riga, serve per il salvataggio della tappa raggiunta.
    Funziona in maniera molto simile al metodo leggiRiga*/
    public String leggiParola(String percorsoFile, int numeroRiga) throws IOException{ 

        try(BufferedReader br = new BufferedReader(new FileReader(percorsoFile))){

            String riga;
            int numRigaCorrente = 1;

            while((riga = br.readLine()) != null){

                if(numRigaCorrente == numeroRiga){

                    if(riga.trim().isEmpty()){
                        return "";
                    }

                    String[] parti = riga.split(";");
                    return parti[0].trim();
                }

                numRigaCorrente++;

            }

        }

        return ("Errore, riga richiesta non presente nel testo.");

    }

    /*Salva il riepilogo dell'ultima partita in un file di testo.
    Il contenuto include il turno raggiunto ela tappa raggiunta durante viaggio.
    Scrive un file in modo che sia successivamente leggibile con il metodo leggiRiga*/
    public void salvaPartita(String percorsoFile, int turnoRaggiunto) throws IOException{
        
        try(FileWriter fileWriter = new FileWriter(percorsoFile)){

            fileWriter.write("Turno raggiunto nella partita precedente: "+turnoRaggiunto);
            fileWriter.write("; ");
            if(turnoRaggiunto<11){

                fileWriter.write("Il tuo viaggio si è concluso nei pressi di: "+leggiParola("tappe.txt", turnoRaggiunto));
                fileWriter.write(" ");

            } else {

                fileWriter.write("Hai raggiunto il tuo obiettivo, raggiungendo la città di "+leggiParola("tappe.txt", turnoRaggiunto-1)+"!");
                fileWriter.write(" ");

            }

        } 

    }
}

    



