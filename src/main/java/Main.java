import dataAccess.ConnectionFactory;
import presentation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        Connection connection =ConnectionFactory.getConnection();
        View view= new View();
        ClientView clientView=new ClientView();
        OrderView orderView=new OrderView();
        ProductView productView=new ProductView();
        Controller controller=new Controller(view,clientView,orderView,productView);
    }
}
