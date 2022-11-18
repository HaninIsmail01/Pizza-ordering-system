/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initiator;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hanin192895
 */
public class initiatorServer implements Runnable {
    Socket client;
    static initiatorGUI i_UI;
    static ArrayList<task> payload = new ArrayList<task>(); 

    public initiatorServer(Socket client) {
        this.client = client;
    }
    @Override
    public void run(){
        float total= 0;
          try{
          
            ObjectInputStream input = new ObjectInputStream(client.getInputStream());
              try {
                  System.out.println(payload.size());
                  payload = (ArrayList<task>)input.readObject();
                   System.out.println(payload.size() +" ");
                  for(int i =0; i<payload.size(); i++){
                      if(payload.get(i).isStatus() == true)
                      total = total +payload.get(i).getPrice();
                  }
                  i_UI.appendTextArea(payload, total);
              } 
              catch (ClassNotFoundException ex) {
                  Logger.getLogger(initiatorServer.class.getName()).log(Level.SEVERE, null, ex);
              }

              input.close();
              client.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        
        
    }
    public static void main(String[] args) {
          payload.add(new task());
            payload.add(new task());
            payload.add(new task());
       try {
            ServerSocket s = new ServerSocket(2000);
           i_UI = new initiatorGUI();
           i_UI.setVisible(true);
            while (true) {
                Socket client = s.accept();
                initiatorServer i = new initiatorServer(client);
                Thread X = new Thread(i);
                X.start();
            }

        } catch (IOException e) {
            System.out.println("Error!");
        }
    }
    
    
}
