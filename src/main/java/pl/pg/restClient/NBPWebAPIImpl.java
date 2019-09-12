package pl.pg.restClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.pg.model.Currency;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NBPWebAPIImpl implements NBPWebAPI {
    String adress = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";
    String format = "?format=json";

    public Currency getExchangeRateOfDollarPublishedFromDateToToday(Date date){
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(getUri(date));
            HttpResponse response = client.execute(request);

            BufferedReader bufReader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = bufReader.readLine()) != null) {
                builder.append(line);
                builder.append(System.lineSeparator());
            }

            Currency currency = new ObjectMapper().readValue(builder.toString(), Currency.class);
            return currency;


        }catch (Exception e){
            e.printStackTrace();
        }


        return null;

    }

    private String getUri(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(new Date());
        return adress + dateFormat.format(date) +"/"+todayDate+"/"+format;
    }
}
