package archive.trawler.persistance;

import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class AdvancedSearchResource {
    public static void main(String[] args) {
        String content = null;
        URLConnection connection;
        try {
            connection =  new URL("https://api.openarch.nl/1.0/records/search.json?name=van%20Lil&lang=nl&number_show=1&sort=1&start=0&archive").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            ex.printStackTrace();
        }
        assert content != null;
        JSONObject json = new JSONObject(content); // Converteer text naar JSONObject
        System.out.println(json.toString(4)); // Print het PRETTY

        // TODO Schrijf een plek om de resultaten weg te schrijven, evt file en dan blob.
        //  zorg ervoor dat deze functie wordt aangeroepen bij opstarten van de server/regelamtig
        //  labels: ARCHIEFTRAWLER
    }
}
