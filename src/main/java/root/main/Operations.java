package root.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.sql.SQLException;

public class Operations {

    public static void main(String[] args) throws SQLException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Listener listener = context.getBean("listener", Listener.class);
    }
}
