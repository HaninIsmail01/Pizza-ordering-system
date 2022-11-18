/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Collaborator2;

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
public class Collaborator2 implements Runnable {
    Socket client = new Socket();
    static Collaborator2UI c_UI ;
    String topping ;
    int price;
    static ArrayList<task> payload;

    public Collaborator2(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
         try{
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());

            try {
                  payload = (ArrayList<task>) input.readObject();
            for (int i =0; i< payload.size(); i++){
                if(payload.get(i).getSeqNumber() == 2 && payload.get(i).isStatus() == false){
                    payload.get(i).setOrders(topping);
                    payload.get(i).setPrice(price);
                    payload.get(i).setStatus(true);
                    System.out.println(topping +"  "+price);
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Collaborator2.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(4000);
            while (true) {
                Socket client = s.accept();
                c_UI = new Collaborator2UI();
                c_UI.setVisible(true);
                Collaborator2 c = new Collaborator2(client);
                Thread x = new Thread(c);
                x.start();
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
