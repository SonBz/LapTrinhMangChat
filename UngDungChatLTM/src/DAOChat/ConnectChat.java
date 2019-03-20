/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOChat;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nguyen Son
 */
public class ConnectChat {
     public static Connection connect(){
        Connection con=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","sonbz","123");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
