/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOChat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Nguyen Son
 */
public class KiemTraLoginDAO {
     private Connection con=null; 
     public int kiemtra(String tentk,String pass){
       con=ConnectChat.connect();
       int result=0;
       String str="select distinct ktlai(?,?) as SoTV from TAIKHOAN";
       PreparedStatement ps=null;
       try {
           ps=con.prepareStatement(str);
           ps.setString(1, tentk);
           ps.setString(2,pass);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               result=rs.getInt("SoTV");
               return result;
           }
           
       } catch (Exception e) {
           e.printStackTrace();
       }
      return -4;
   }
}
