/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1938811_planemanagement;
import java.util.Scanner;

/**
 *
 * @author vitoriafranca
 */
public class person {
    private String name;
    private String surname;
    private String email;
    
    public person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    
    public void setName(String name) {
        Scanner userInput = new Scanner(System.in);
        
        this.name= userInput.next();
    }
    public String getName() {
        return this.name;
    }
    public void setSurname(String surname) {
        this.surname= surname;
    }
    public String getSurname() {
        return this.surname;
    }
    public void setEmail(String email) {
        this.email= email;
    }
    public String getEmail() {
        return this.email;
    }
    
}

