package root.binance;

import root.main.Connections;



import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class BinanceConnector implements Connector {

    public InputStreamReader getResponse(String filter) {
        try {
            HttpURLConnection connection = Connections.getBinanceHttpURLConnection(filter);

            connection.setRequestProperty("accept", "application/json");

            InputStream responseStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(responseStream);
            return reader;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
