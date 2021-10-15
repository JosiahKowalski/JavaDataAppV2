
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Scanner;

/*
 * possibly use the JSON Deserializer
 * https://www.tutorialspoint.com/how-to-deserialize-a-json-to-java-object-using-the-flexjson-in-java
 */

public class JavaDataAppV2 {
    String ticker, URL;

    public JavaDataAppV2() {
    }

    //Receives user input
    public String input() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter any stock ticker or press x to exit: ");
        ticker = in.next();
        if (ticker.equals("x")) {
            return "";
        }
        ticker = ticker.toUpperCase();
        return buildURL(ticker);
    }

    //Generates the URL using user input
    public String buildURL(String ticker) {
        return "https://rest.coinapi.io/v1/exchangerate/"+ticker+"/USD";
    }

    public String[] getDateTime(JSONObject stock) {
        String dateTime = stock.getString("time");
        String dateTimeStr = dateTime.replaceAll("^\"|\"$", "");;
        String[] dateTimeArr = dateTimeStr.split("T");
        dateTimeArr[1] = dateTimeArr[1].replace(".0000000Z", "");
        return dateTimeArr;
    }

    //Builds the output string
    public String printStockString(JSONObject stock) {
        String[] dateTime = getDateTime(stock);
        String base = stock.getString("asset_id_base");
        String quote = stock.getString("asset_id_quote");
        double rate = stock.getDouble("rate");

        return String.format("Date: %s\nTime: %s\nTicker: %s\nQuote: %s\nPrice: %f.3",dateTime[0], dateTime[1], base, quote, rate);
//        System.out.println(string);
//        System.out.println("Price: "+rate);
    }

    //Handles the URL
    public String handleURL(String http) throws UnirestException {
        String x_coinapi_key = "E45F127F-46D7-46C3-8C5E-4A96413B319C";
        HttpResponse<JsonNode> response = Unirest.get(http)
                .header("X-CoinAPI-Key", x_coinapi_key)
                .asJson();
        try {
            JsonNode jn = response.getBody();
            JSONObject jso = jn.getObject();
            return printStockString(jso);
        } catch (Exception e) {
//            System.out.println("This ticker doesn't seem to be in our records.");
//            System.out.println("Try another.");
            return null;
        }
    }

    public static void main(String[] args) throws UnirestException {
        JavaDataAppV2 data = new JavaDataAppV2();
        while (true) {
            String my_url = data.input();
            if (my_url.equals("")) {
                System.out.println("Exiting program");
                break;
            }
            data.handleURL(my_url);
        }

        //String URL = "https://rest.coinapi.io/v1/exchangerate/"+ticker+"/USD?apikey=E45F127F-46D7-46C3-8C5E-4A96413B319C";
        //String URL = "https://rest.coinapi.io/v1/exchangerate/USD?apikey=E45F127F-46D7-46C3-8C5E-4A96413B319C&invert=true&output_format=csv";
        //Backup plan if URL doesnt work, use this CSV
        //String URL = "https://rest.coinapi.io/v1/exchangerate/USD?apikey=E45F127F-46D7-46C3-8C5E-4A96413B319C";
    }
}
