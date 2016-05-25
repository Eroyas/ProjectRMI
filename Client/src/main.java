import JMS.ClientJMS;
import LibraryService.ClientLibrary;
import PizzaService.ClientPizza;

import java.util.Scanner;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class main {
    public static void main(String[] args){
        System.out.println("Quel client voulez vous lancez ? ");
        System.out.println("Entrez 1 pour le client RMI Library.");
        System.out.println("Entrez 2 pour le client JMS.");
        System.out.println("Entrez 3 pour le client RMI Pizza avec JMS.");

        Scanner scanner = new Scanner(System.in);

        switch(scanner.nextInt()) {
            case 1:
                ClientLibrary.clientLibrary();
                break;
            case 2:
                ClientJMS.clientJMS();
                break;
            case 3:
                ClientPizza.clientPizza();
                break;
            default:
                System.out.println("Désolé, l'entré n'est pas valide.");
        }

    }
}
