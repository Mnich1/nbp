package pl.pg.viewer;

import org.springframework.stereotype.Component;
import pl.pg.model.Currency;

@Component
public class Viewer {

    public void viewCurrency(Currency currency){
        System.out.println(currency.toString());
    }
}
