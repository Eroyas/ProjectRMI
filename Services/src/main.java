import JMS.ServiceJMS;
import LibraryService.ServiceLibrary;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public class main {
    public static void main(String[] args){
        try {
            ServiceLibrary.serviceLibrary();
        }catch (Exception e){
            System.err.println("There was a problem in ServiceLibrary");
            e.printStackTrace();
        }
        try {
            ServiceJMS.serviceJMS();
        }catch (Exception e){
            System.err.println("There was a problem in ServiceJMS");
            e.printStackTrace();
        }
        try {
            //ServicePizza.servicePizza();
        }catch (Exception e){
            System.err.println("There was a problem in ServicePizza");
            e.printStackTrace();
        }
    }
}
