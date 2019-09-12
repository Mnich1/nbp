package pl.pg.framework;

import org.springframework.beans.factory.annotation.Autowired;
import pl.pg.model.Currency;
import pl.pg.restClient.NBPWebAPI;
import pl.pg.restClient.NBPWebAPIImpl;
import pl.pg.viewer.Viewer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Framework {
    //@Autowired
    NBPWebAPI nbpWebAPI = new NBPWebAPIImpl();
    Viewer viewer = new Viewer();


    public void startFramework() {
        Date date = getDate();
        Currency currency = getCurrency(date);
        viewer.viewCurrency(currency);
    }

    private Currency getCurrency(Date date) {
        return nbpWebAPI.getExchangeRateOfDollarPublishedFromDateToToday(date);
    }

    private  Date getDate() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Please enter starting date in format yyyy-MM-dd. Application will count currency rate from that date since today: ");
            String startDate = br.readLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(isThisDateValid(startDate, dateFormat)){
                Date date = dateFormat.parse(startDate);;
                return date;
            }
            else{
                System.out.println("Proper date format is yyyy-MM-dd");

        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  getDate();
    }


    public boolean isThisDateValid(String dateToValidate, SimpleDateFormat sdf) {

        if (dateToValidate == null) {
            return false;
        }

        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

}
