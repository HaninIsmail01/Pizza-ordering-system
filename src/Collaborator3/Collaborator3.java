/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collaborator3;

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
public class Collaborator3 implements Runnable {
    Socket client = new Socket();
    static Collaborator3UI c_UI;
    String sauce;
    int price;
    static ArrayList<task> payload;
    
    public Collaborator3(Socket client) {
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
                if(payload.get(i).getSeqNumber() == 3 && payload.get(i).isStatus() == false){ 
                    payload.get(i).setOrders(sauce);
                    payload.get(i).setPrice(price);
                    payload.get(i).setStatus(true);
                    System.out.println(sauce +" "+ price);
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Collaborator3.class.getName()).log(Level.SEVERE, null, ex);
            }
            input.close();
            in.close();
            client.close();
     

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
      public static void main(String[] args) {
        try{
            ServerSocket s = new ServerSocket(5000);
            while(true){
                Socket client = s.accept();
                c_UI = new Collaborator3UI();
                c_UI.setVisible(true);
                Collaborator3 c = new Collaborator3(client);
                Thread x = new Thread(c);
                x.start();
            }
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
}
