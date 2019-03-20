/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import serverchat.ServerChat;
import DAOChat.KiemTraLoginDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nguyen Son
 */
public class ThreadLogin extends Thread {

    private Socket socket;
    public ServerChat server;
    private String tenTk = "";
    private String pass = "";
    private int reults = 0;
    private boolean flag = true;
    private DataInputStream dis = null;
    private DataOutputStream dos = null;
    private StringTokenizer tokenizer;

    public ThreadLogin(ServerChat server, Socket socket) {
        this.socket = socket;
    }

    public void guiTinNhan2(String mess) {
        Enumeration e = ServerChat.listUser.keys();
        String name = null;
        while (e.hasMoreElements()) {
            name = (String) e.nextElement();
            ServerChat.listUser.get(name).gui(mess);
        }
    }

    public void gui(String mess) {
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(mess);

        } catch (IOException ex) {
            Logger.getLogger(ThreadLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //hien thi các user 

    public String getAllName() {
        Enumeration e = ServerChat.listUser.keys();
        String name = "";

        while (e.hasMoreElements()) {
            name += (String) e.nextElement() + "|";
        }
        return name;
    }

    public void senadAll() {
        Enumeration e = ServerChat.listUser.keys();
        String name = "";
        while (e.hasMoreElements()) {
            name = (String) e.nextElement();         
               ServerChat.listUser.get(name).gui("Online_User|" + getAllName());     
        }
    }

    public void ExitChat() {
        try {
            dis.close();
            dos.close();
            socket.close();
            System.out.println("da dong");
        } catch (IOException ex) {
            Logger.getLogger(ThreadLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        String message, cmd, name;
        while (true) {
            try {
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                message = dis.readUTF();
                tokenizer = new StringTokenizer(message, "|");
                cmd = tokenizer.nextToken();
                switch (cmd) {
                    case "Ten":
                        name = tokenizer.nextToken();
                        ServerChat.listUser.put(name, this);
                      
                        break;
                    case "KT_Login":
                        do {
                            tenTk = tokenizer.nextToken();
                            pass = tokenizer.nextToken();
                            reults = new KiemTraLoginDAO().kiemtra(tenTk, pass);
                            if (reults == 1) {
                                dos.writeInt(1);
                                ExitChat();
                                flag = false;
                            } else if (reults == -1) {
                                dos.writeInt(-1);
                                flag = false;
                            } else if (reults == -2) {
                                dos.writeInt(-2);
                                flag = false;
                            }
                        } while (flag);
                        break;
                    case "TinNhan":
                        String str = tokenizer.nextToken();
                        guiTinNhan2("TinNhan|" + str);

                        break;
                    case "Online_User":                     
                        senadAll();
                        break;
                    case "Thoat":
                        String nameT = tokenizer.nextToken();
                        System.out.println(" Tai Khoản Thoát : "+nameT);
                        guiTinNhan2("Thoat|-" + nameT + "- Đã Thoát");
                        ServerChat.listUser.remove(nameT, this);
                        senadAll();
                     
                        break;
                }

            } catch (Exception e) {
                try {
                    dis.close();
                    dos.close();
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    }
}
