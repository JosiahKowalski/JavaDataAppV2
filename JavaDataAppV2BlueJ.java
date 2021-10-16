import javax.json.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * This class uses an API (https://docs.coinapi.io/?java#exchange-rates) to get real time stock prices.
 *
 * @author Josiah
 * @author Michael
 */
public class JavaDataAppV2BlueJ {     
    String URL;

    //Generates the URL using user input
    public String buildURL(String stock, String currency) {
        String URL = "https://rest.coinapi.io/v1/exchangerate/"+stock+"/"+currency+"?apikey=E45F127F-46D7-46C3-8C5E-4A96413B319C";
        return URL;
    }
    
    public String[] getDateTime(JsonObject stock) {
        String dateTime = stock.getString("time");
        String dateTimeStr = dateTime.replaceAll("^\"|\"$", "");;
        String[] dateTimeArr = dateTimeStr.split("T");
        dateTimeArr[1] = dateTimeArr[1].replace(".0000000Z", "");
        return dateTimeArr;
    }

    //Builds the output string
    public String printStockString(JsonObject stock) {
        String[] dateTime = getDateTime(stock);
        String base = stock.getString("asset_id_base");
        String quote = stock.getString("asset_id_quote");
        JsonNumber rate = stock.getJsonNumber("rate");

        return String.format("Date: %s  Time: %s  Ticker: %s  Quote: %s  Price: "+ rate, dateTime[0], dateTime[1], base, quote);
    }

    //Handles the URL 
    public JsonObject handleURL(String http) {
        InputStream is = openURL(http);
        try {
            JsonReader jsonReader = Json.createReader(is);
            JsonStructure js = jsonReader.read();
            jsonReader.close();
            closeStream(is);
            JsonObject jso = null;
            return jso = (JsonObject) js;
        } catch (Exception e) {
            return null;
        }
    }

    // copied from Jason Millers Bike project
    private static InputStream openURL (String http) {
        URL url;
        InputStream source = null;
        try {
            url = new URL(http);
            source = url.openStream();
        } catch (Exception e) {
            System.err.println("Cannot open URL "+http);
            System.err.println(e);
        }
        return source;
    }

    // copied
    private static void closeStream (InputStream is) {
        try {
            is.close();
        } catch (Exception e) {
            System.err.println("Could not close the input stream.");
            System.err.println(e);
        }
    }
}
