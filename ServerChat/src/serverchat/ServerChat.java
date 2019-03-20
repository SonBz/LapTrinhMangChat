/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverchat;

import DAOChat.KiemTraLoginDAO;

import Thread.ThreadLogin;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Son
 */
public class ServerChat {
     private int port;
     private boolean flag=true;
     public static Hashtable<String,ThreadLogin> listUser;
    public ServerChat(int port) {
        this.port = port;
    }
    
    public void execute() throws IOException{
        ServerSocket server=new ServerSocket(port);
        System.out.println("doi client ....");
        listUser=new Hashtable<String, ThreadLogin>();
        while (true) {            
             Socket socket=server.accept();                
             ThreadLogin login=new ThreadLogin(this,socket);
             login.start();
               
           
        }
   
       
      
    }
    public static void main(String[] args) {
        ServerChat sv=new ServerChat(3333);
        try {
            sv.execute();
        } catch (IOException ex) {
            Logger.getLogger(ServerChat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
