/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1938811_planemanagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author vitoriafranca
 */
public class Ticket {
        private static person customer;
    private static String row;
    private static int seat, price;

    public Ticket(person customer, String row, int seat, int price) {
        this.customer = customer;
        this.row = row;
        this.seat = seat;
        this.price = price;
    }
    
    public void setCustomer(person customer) {
        this.customer = customer;
    }
    
    public person getCustomer() {
        return customer;
    }

    
    public String getRow() {
        return row;
    }
    
    public void setRow(String row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }
    
    public void setSeat(int seat) {
        this.seat = seat;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }

    public static void save() {
        try {
        File file = new File("Ticket"+row+seat+".txt");
        boolean file_created = file.createNewFile();
        
        if (file_created){
            System.out.println("File created: " + file.getName());
            FileWriter writeTxt = new FileWriter(file);
            writeTxt.write("Seat booked: "+row+seat+"\nCustomer's full name: "+customer.getName()+customer.getSurname()+"\nCustomer's email: "+customer.getEmail());
            writeTxt.close();
        } 
        else {
            if (file.exists()){
                System.out.println("File already exists.");
            } 
            else{
                System.out.println("Error while creating file: " + file.getName());
                }
        }
        }
         catch(IOException e){
             e.printStackTrace();
         }
    }
    
        
}

