/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initiator;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author hanin192895
 */
public class task implements Serializable {
    static int counter= 0;
    int seqNumber;
    boolean status;
    String orders ;
    int price ;
    
    public task(boolean status) {
        this.seqNumber= ++counter;
        this.status = status;
    }

    public task() {
         this.seqNumber = ++counter;
        this.status = false;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSeqNumber() {
        return seqNumber;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "task{" + "seqNumber=" + seqNumber + ", status=" + status + ", orders=" + orders + ", price=" + price + '}';
    }

    
    
}
