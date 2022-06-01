package root.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import root.binance.BinanceAdapter;
import root.binance.BinanceConnector;
import root.binance.CurrenciesRate;
import root.controller.Controller;
import root.controller.SpringController;
import root.loggers.LoggerToDB;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Operations {

    public static void main(String[] args) throws SQLException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Listener listener = context.getBean("listener", Listener.class);
    }
}
