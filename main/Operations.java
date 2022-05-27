package main;

import binance.BinanceAdapter;
import binance.BinanceConnector;
import binance.CurrenciesRate;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Operations {

    public static void main(String[] args) throws SQLException, IOException {
        /*model.Model model = new model.Model();
        view.View view = new view.View();
        Connection sqlConnection = main.Connections.getSQLConnection("mysql");

        loggers.AbstractLogger logger = new loggers.LoggerToDB(new storage.StorageSqlImpl(sqlConnection));

        controller.ControllerImpl controller1 = new controller.ControllerImpl(model, view, logger);
        controller.ControllerImpl2 controller2 = new controller.ControllerImpl2();

        main.Listener listener = new main.Listener(new controller.Controller[]{controller1, controller2}, logger);

        // controller.ControllerImpl/processIncomingInformation 5 5 *
        sqlConnection.close();*/


        BinanceConnector connector = new BinanceConnector();

        BinanceAdapter adapter = new BinanceAdapter(connector);

        //CurrenciesRate obj = adapter.getRate("ETHBTC");

        List<CurrenciesRate> list = adapter.getAllConversionPairs();

        //System.out.println(obj.toString());

        System.out.println("Available options: ");

        for (CurrenciesRate one: list) {
            System.out.println(one.toString());
        }
    }
}
