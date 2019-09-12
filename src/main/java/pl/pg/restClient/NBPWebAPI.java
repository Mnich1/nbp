package pl.pg.restClient;

import org.springframework.stereotype.Component;
import pl.pg.model.Currency;

import java.util.Date;
@Component
public interface NBPWebAPI {

    Currency getExchangeRateOfDollarPublishedFromDateToToday(Date date);

}
