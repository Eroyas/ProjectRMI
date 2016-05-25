import JMS.ClientJMS;
import LibraryService.ClientLibrary;

import java.util.Scanner;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class main {
    public static void main(String[] args){
        System.out.println("Quel client voulez vous lancez ? ");
        System.out.println("entrez 0 pour le client Library.");
        System.out.println("entrez 1 pour le client JMS.");
        System.out.println("entrez 2 pour le client Pizza.");
        Scanner scanner = new Scanner(System.in);
        switch(scanner.nextInt()){
            case 0:
                ClientLibrary.clientLibrary();
                break;
            case 1:
                ClientJMS.clientJMS();
                break;
            case 2:
                //ClientPizza.clientPizza();
                break;
            default:
                System.out.println("Désolé, l'entré n'est pas valide.");
        }

    }
}
