package PizzaService;

/**
 * Created by corentinhardy on 25/05/2016.
 */
public interface IPizza {

    String[] list();

    boolean commandePizza(String nomPizza, String nomReserve);
}
