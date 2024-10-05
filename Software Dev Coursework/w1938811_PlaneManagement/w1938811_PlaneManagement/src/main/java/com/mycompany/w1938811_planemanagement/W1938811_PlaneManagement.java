/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.w1938811_planemanagement;

/**
 *
 * @author vitoriafranca
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class W1938811_PlaneManagement {

        static String[] seatRows= {"A","B","C","D"};
        //created a double array for the plane seats 
        static int[][] planeSeats = new int[4][];
        //setting boundaries for a maximum amount of tickets and to store tickets
        static Ticket [] ticketInfo = new Ticket [52];
        // priceCount is to count how many tickets were sold (1,2... etc)
        static int priceCount;
        // total ticket sales (adds all tickets purchased)
        static int totalSales = 0;
        

  //start of the main
    public static void main(String[] args) {
        
        //defining the size of the arrays        
        planeSeats[0] = new int[14]; //row A 
        planeSeats[1] = new int[12]; //row B
        planeSeats[2] = new int[12]; //row C
        planeSeats[3] = new int[14]; //row D
        
        //boolean for menu, so while its true, it loops. When user quits, it sets to false and ends.
        boolean menuLoop = true;
    
        
        //creation of the array for rows and seats
        //String[] seatRows = {"A","B","C","D"};
        Scanner menuInput = new Scanner(System.in);
        //while loop, loop the menu while menuLoop is equal to true
        while (menuLoop){
        System.out.println(" ".repeat(8) + "Welcome to the Plane Management Application" + " ".repeat(10));

        // now, to print the menu stars we use the repeat function.
        System.out.println("*".repeat(60));
        System.out.println("*" + " ".repeat(28) + "MENU" + " ".repeat(26) + "*");
        System.out.println("*".repeat(60) + "\n");
        //main menu options
        System.out.println("1)Buy a seat \n2)Cancel a seat\n3)Find first available seat\n4)Show seating plan\n5)Print tickets information and total sales\n6)Search ticket\n0)Quit");
        
// a switch for the user to enter a number to choose how to proceed.
                if(menuInput.hasNextInt()){
                   int userInput = menuInput.nextInt();
                    switch(userInput) {
                        case 1: //if user enters 1, "buy seat" executes1
                            buy_seat();
                            Ticket.save();
                            break;
                        case 2: 
                            cancel_seat(); // if user enters 2, "cancel seat" executes
                            break;
                        case 3:
                            findFirstAvailable(); // if user enters 3, it searches for first seat available
                            break;                           
                        case 4:
                            show_seats(); //shows seat plan
                            break;                            
                        case 5:
                            printTicketInfo(); //prints ticket info, at the moment only prints price to pay.
                            break;                           
                        case 6:
                            searchTicket(); // searches for a booked seat, prints info of who booked
                            break;
                        case 0: 
                            System.out.println("Quit successful"); // if the user enters 0, menuloop sets to false, ending the loop.
                            menuLoop = false;
                            break;
                        default:
                            System.out.println("Sorry, this is not a valid option"); //error if they enter a not valid integer.
                            break;                            
                    } 
                    //the below else is in case they enter a string, not a int. 
                } else {
                    System.out.println("That is not correct, please enter a valid integer");
                    menuInput.next(); //since they entered a letter or something else and it isn't valid, the menu pops up again.

                }
        }
    }
        
//creating a method to show the seating plan
public static void show_seats(){
    // a for loop that iterates through seatRows A, B, C D
        for (int i = 0; i < planeSeats.length; i++) {
            System.out.print(seatRows[i]+" "); //
            //another for loop, nested in this case that prints the columns (seats)
            for (int j = 0; j < planeSeats[i].length ; j++) {
              if (planeSeats[i][j] == 1) {
                System.out.print("X ");//if j=1 (seat is booked) then print an X instead of 1 
            } 
            else {
                System.out.print("0 "); //if it's not booked, then print a 0
              }
        }        
            System.out.println(); //print everything and each iteration through a loop, in a new line.
        }     
}
//method to buy a seat.
public static void buy_seat(){
Scanner userInput = new Scanner(System.in);
System.out.println("Enter a row and a seat number: \n");

//asking the user to input a row and then changes it to upper case
String userRow = userInput.next().toUpperCase();
//asking user to enter a seat number
int userColumn = userInput.nextInt();
//setting two variables to false
boolean validRow = false;
boolean validColumn= false;

//created a list for to save person (for the person class)
person[] list_Persons = new person[3];


if (isSeatValid(userRow, userColumn)) {
    //checks if the seat is valid (ensuring it's within valid ranges)
            int rowIndex = -1; // -1 to indicate that we have not found the row yet
            //for loop that iterates through the rows
            for (int i = 0; i < seatRows.length; i++) {
                if (seatRows[i].equals(userRow)) {
                    //checks if the userRow (row entered by user) matches any in seatRows
                    rowIndex = i;
                    //if found, store it then break
                    break;
                }         
                }
          
            //checks if what the user entered (-1 to match computer index) is occupied or not
            if (planeSeats[rowIndex][userColumn - 1] == 0) {
                //if the input from row and usercolumn (-1 to match computer index) equals 0 then, ask for the passenger details (only if it's not boked)!
                planeSeats[rowIndex][userColumn - 1] = 1;
                //since it was equal to 0, it sets it to one and asks for the customer information.
                System.out.println("Please enter the passenger's first name: ");
                String fn = userInput.next(); // take next user input and store it in fn (full name)
                System.out.println("Please the passenger's surname: ");
                String ln = userInput.next(); // take next user input and store it in ln (last name)
                System.out.println("Please enter an email address: ");
                String em = userInput.next(); // take next userinput and store it in em (email)
                person customer = new person(fn, ln, em); // stores this info into person class.
                
            // Set prices based on columns
                   int price= 0; //variable for price
                   if ((userColumn -1) >= 0 && (userColumn - 1) <= 4) { //-1 to match list index 
                        price = 200; // Price is 200 for seats 0-4
                        System.out.println("The value of the ticket is: £200");
                    } else if ((userColumn -1) >= 5 && (userColumn - 1) <=8) { //
                        price = 150; // Price is 150 for seats 5-8
                        System.out.println("The value of the ticket is: £150");
                    } else if ((userColumn -1) >= 9 && (userColumn - 1) <= 13) {
                        price = 180; // Price is 180 for seats 9-13
                        System.out.println("The value of the ticket is: £180");
                    }
                Ticket newTicket  = new Ticket(customer,userRow, userColumn, price); //store this information in Ticket class
                totalSales+= price; // adds the price to total sales, adding it up for when customer wants to select option 5
                ticketInfo[priceCount]= newTicket;
                priceCount++; //adds to price count (how many tickets have been bought: 1, 2.. etc)
                System.out.println("Seat " + userRow + userColumn + " purchased successfully!"); //if seat free and user entered their information, seat is purchased
                
            } else { //print if the seat the customer entered is not 0
                System.out.println("Seat " + userRow + userColumn + " is already occupied.");
            }
        } else { //if the input is not within range (between rows A,B,C,D and their respective lenghts, asks for the input again)
            System.out.println("Invalid row or seat number entered. Please try again.");
            buy_seat(); //repeat the buy_seat method
        }
    }
    //method to check if the user input is within range 
    public static boolean isSeatValid(String row, int column) { 
        row = row.toUpperCase(); //to ensure the code still works if they enter a lower case, so it converts it to upper case
        boolean validRow = false; //indicates that validRow is false as it has not yet been found
        for (String validRowName : seatRows) {
            if (validRowName.equals(row)) {//checks if the row is equal to seatRows A, B, C or D
                validRow = true; // valid, so sets it to true and then ends the program
                break;
            }
            }
        
        boolean validColumn = (column >= 1 && column <= planeSeats[0].length); //checks if columns (seats) are within range
        return validRow && validColumn;
}

   // method to cancel seat
    public static void cancel_seat() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter a row and a seat number: \n"); // asks for user input

        //asking the user to input a row
        String userRow = userInput.next().toUpperCase();
        //asking user to enter a seat number
        int userColumn = userInput.nextInt();
        
        boolean validRow = false;
        boolean validColumn= false;
//checks if the seat is valid using isSeatValid method
    if (isSeatValid(userRow, userColumn)) {
    //checks if the seat is valid (ensuring it's within valid ranges)
            int rowIndex = -1; // -1 to indicate that we have not found the row yet
            for (int i = 0; i < seatRows.length; i++) {
                if (seatRows[i].equals(userRow)) {
                    //checks if the userRow (row entered by user) matches any in seatRows
                    rowIndex = i;
                    //if found, store it then break
                    break;
                }
            }
            //checks if what the user entered (-1 to match computer index) is occupied or not
            if (planeSeats[rowIndex][userColumn - 1] == 1) {
                //similar to buy seat, instead this if statement checks if the seat is = to 1 (booked), then changes it to 0
                planeSeats[rowIndex][userColumn - 1] = 0; //once changed to 0 it prints that it was cancelled successfully
                System.out.println("Seat " + userRow + userColumn + " cancelled successfully!");
            } else { //if the seat is not equal to 1, meaning it wasn't booked
                System.out.println("Seat " + userRow + userColumn + " is already free.");
            }
        } else { //if the values entered were not valid
            System.out.println("Invalid row or seat number entered. Please try again.");
            cancel_seat();
        }
    }
    

    //find first seat available method
    public static void findFirstAvailable() {
    boolean seatFound = false; //set the variable seatFound to false, because seat has not been found
    //Iterate through each row 
    for (int i = 0; i < planeSeats.length; i++) {
        //iterates through planeSeats row and then columns for each row
            System.out.print(seatRows[i]+" ");
            for (int j = 0; j < planeSeats[i].length ; j++) {
                //finds the first "0" value in the array and then prints it to show it is available (because "1" value would mean it is occupied)
                if (planeSeats[i][j] == 0) { // if the value is equal to 0 (available) then prints the following:
                    System.out.println("Seat " + seatRows[i] + (j + 1) + " is available");
                    //changes seat found to true and then ends the loop
                    seatFound =true;
                    break;
                }
            }
        // if the seat is found, breaks it. If this wasn't here, it would print the first available for each. 
        if (seatFound){
            break;
                    
                }
                    
            }
    }
    //print ticket info (total ticket sales)
    public static void printTicketInfo(){
        System.out.println("Amount to pay is equal to £"+ totalSales + ". Thank you for your purchase!"); 
    }
    
    //method created for the search ticket method
    public static int getRowIndex(String row) {
        //iterates through the rows to check if its valid
    for (int i = 0; i < seatRows.length; i++) {
        if (seatRows[i].equals(row)) { // if the row entered matches an existing one
            return i; //returns the value of i 
        }
    }
    return -1; 
    }
    //search ticket method (to find a specific seat)
   public static void searchTicket() {
   Scanner userInput = new Scanner(System.in);
        System.out.println("Enter a row and a seat number: \n");

        //asking the user to input a row
        String userRow = userInput.next().toUpperCase();
        //asking user to enter a seat number
        int userColumn = userInput.nextInt();

        //boolean validRow = false;
        //boolean validColumn= false;
        int rowIndex= getRowIndex(userRow);
    
    if (isSeatValid(userRow, userColumn)){ //checks if valid
        if (userColumn >= 1 && userColumn <= planeSeats[rowIndex].length) {
            // Check if the requested seat is occupied
            if (planeSeats[rowIndex][userColumn - 1] == 0) { //if it is equal to 0 (free)
                System.out.println("Seat " + seatRows[rowIndex] + userColumn + " is available");
            } else {
                System.out.println("Sorry, this seat " + seatRows[rowIndex] + userColumn + " is already occupied.");
                    boolean seatBooked = false; //boolean for Seat booked, set to false since it has not been found
                // Iterate through ticketInfo to find the corresponding Ticket object
                    for (Ticket ticket : ticketInfo) {
                        if (ticket != null && ticket.getRow().equals(userRow) && ticket.getSeat() == userColumn) {
                            // Access the customer information from the Ticket object
                            System.out.println("Booked by: \n" + ticket.getCustomer().getName() +" " + ticket.getCustomer().getSurname());
                            System.out.println("Email address: " + ticket.getCustomer().getEmail());
                            System.out.println("Ticket cost: £"+ ticket.getPrice()+"\n");
                            seatBooked= true;
                            break; // Exit the loop once the matching Ticket object is found
                            }
                         }
                    // If the seat is not found, prints the following message
                    if (!seatBooked) {
                        System.out.println("Great news! This seat is not booked." + userRow);
                    }
                }
             } else { 
                System.out.println("Invalid seat number. Please enter a seat within the range.");
            }
        } else { 
            System.out.println("Invalid row or seat number entered. Please try again.");
        }
    }
}
   


    
