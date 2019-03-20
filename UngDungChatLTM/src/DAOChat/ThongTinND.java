/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOChat;

import Controller.NguoiDung;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Nguyen Son
 */
public class ThongTinND {
    private Connection con=null;  
 public void DangKi(NguoiDung nd){
        con=ConnectChat.connect();
      
        String str="insert into taikhoan(tentk,pass,ten,gioitinh,sdt,ngaysinh) values(?,?,?,?,?,?)";
        try {
           PreparedStatement ps=con.prepareStatement(str);
            ps.setString(1, nd.getTenTk());         
            ps.setString(2, nd.getPass());
            ps.setString(3, nd.getTen());
            ps.setString(4, nd.getGioiTinh());
            ps.setInt(5, nd.getSdt());
            ps.setDate(6, new Date(nd.getNgaySinh().getTime()));
            
            ps.executeUpdate();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public ArrayList loadFile(){
     con=ConnectChat.connect();
     ArrayList<NguoiDung> list=new ArrayList<>();
     String str="select * from taikhoan";
      try {
             PreparedStatement ps=con.prepareStatement(str);
             ResultSet rs=ps.executeQuery();
             while (rs.next()) {                
               NguoiDung nd=new NguoiDung(rs.getString(1));
                list.add(nd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     return list;
 }
}
