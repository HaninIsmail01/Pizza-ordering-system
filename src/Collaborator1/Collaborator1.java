/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collaborator1;
import java.net.*;
import java.io.*;
import java.util.*;
import initiator.task;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author hanin
 */
public class Collaborator1 implements Runnable {
    Socket client = new Socket();
    static Collaborator1UI c_UI ;
    String crust ;
    int price;
    static ArrayList<task> payload;
    public Collaborator1(Socket client) {
        this.client= client;
    }

    @Override
    public void run(){
        try{
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());

            try {
                  payload = (ArrayList<task>) input.readObject();
            for (int i =0; i< payload.size(); i++){
                if(payload.get(i).getSeqNumber() == 1 && payload.get(i).isStatus() == false){ 
                    payload.get(i).setOrders(crust);
                    payload.get(i).setPrice(price);
                    payload.get(i).setStatus(true);
                    System.out.println(crust +" "+ price);
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Collaborator1.class.getName()).log(Level.SEVERE, null, ex);
            }
            input.close();
            in.close();
            client.close();
     

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    
        public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(3000);
            while (true) {
                Socket client = s.accept();
                c_UI = new Collaborator1UI();
                c_UI.setVisible(true);
                Collaborator1 c = new Collaborator1(client);
                Thread x = new Thread(c);
                x.start();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
