package ui;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class MainMenu {

    public static void printMainMenu() {
        HotelResource hotelResource = new HotelResource();
        String regex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(regex);
        String addedEmail = null;
        String addedFirstName = null;
        String addedLastName = null;
        boolean loop;

        //main menu
        String split = "-----------------------------------------------------";
        int command = 0;

        //print the mainmenu
        System.out.println("\nWelcome to the Hotel Reservation Application");
        System.out.println(split);
        System.out.printf("%s%n%s%n%s%n%s%n%s%n%n", "1. Find and reserve a room", "2. See my reservation", "3. Create an Account"
                , "4. Admin", "5. Exit");
        System.out.println(split);
        System.out.println("Please select a number for the menu option");

        //receive command from customers
        Scanner in = new Scanner(System.in);

        try {
            command = in.nextInt();
            if (command > 5 || command <1){
                System.out.println("You input is wrong. Please select a number from 1 to 5");
                MainMenu.printMainMenu();
            }
        }catch (Exception e){
            System.out.println("You input is wrong. Please select a number from 1 to 5");
            MainMenu.printMainMenu();
        }


        //filter those wrong command


            //different command have different function
        switch (command) {

            case 1:

                //find rooms available
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date addedCheckInDate = null;
                Date addedCheckOutDate = null;
                Set<IRoom> availableRooms = new HashSet<>();
                IRoom addedRoom;
                String addedRoomNumber;

                do {
                    try {
                        System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
                        String checkindate = in.next();
                        addedCheckInDate = format.parse(checkindate);
                        loop = false;

                    } catch (Exception e) {
                        System.out.println("Your input is invalid\n");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                do {
                    try {
                        System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/21/2020");
                        String checkoutdate = in.next();
                        addedCheckOutDate = format.parse(checkoutdate);
                        loop = false;
                    } catch (Exception e) {
                        System.out.println("Your input is invalid");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                availableRooms = hotelResource.findARoom(addedCheckInDate, addedCheckOutDate);
                for (IRoom room : availableRooms) {
                    System.out.println(room.toString());
                }

                if (availableRooms.isEmpty()){
                    System.out.println("Recommended Room");
                    Calendar incalendar = Calendar.getInstance();
                    Calendar outcalendar = Calendar.getInstance();
                    incalendar.setTime(addedCheckInDate);
                    outcalendar.setTime(addedCheckOutDate);
                    incalendar.add(Calendar.DATE, 7);
                    outcalendar.add(Calendar.DATE, 7);

                    Date recommendCheckinDate = incalendar.getTime();
                    Date recommendCheckoutDate = outcalendar.getTime();
                    System.out.println("Checkin Date: " + recommendCheckinDate + "    Checkout Date:" + recommendCheckoutDate);

                    availableRooms = hotelResource.findARoom(recommendCheckinDate, recommendCheckoutDate);
                    for (IRoom room : availableRooms) {
                        addedCheckInDate = recommendCheckinDate;
                        addedCheckOutDate = recommendCheckoutDate;
                        System.out.println(room.toString());
                    }

                }

                //ask the customer if they would like book the listed room
                do {
                    System.out.println("Would you like to book a room y/n");
                    try {
                        String yn = in.next();
                        if (yn.equals("y") || yn.equals("Y") || yn.equals("Yes")) {
                            loop = false;
                        } else if (yn.equals("N") || yn.equals("n") || yn.equals("No")) {
                            loop = false;
                            AdminMenu.printAdminMenu();
                        } else {
                            System.out.println("Please enter Y (Yes) or N (No)");
                            loop = true;
                        }
                    } catch (Exception ex) {
                        System.out.println("Please enter Y (Yes) or N (No)");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                //ask the customer if they have an account
                do {
                    System.out.println("Do you have an account with us? y/n");
                    try {
                        String yn = in.next();
                        if (yn.equals("y") || yn.equals("Y") || yn.equals("Yes")) {

                            //check if the email format is right
                            do {
                                try {
                                    System.out.println("Enter Email format: name@domain.com");
                                    String mail = in.next();
                                    if (pattern.matcher(mail).matches()) {
                                        addedEmail = mail;
                                        loop = false;
                                    } else {
                                        System.out.println("Wrong email format.");
                                        loop = true;
                                    }
                                } catch (Exception exception) {
                                    System.out.println("Wrong email format.");
                                    in.next();
                                    loop = true;
                                }
                            } while (loop);
                        } else if (yn.equals("N") || yn.equals("n") || yn.equals("No")) {
                            loop = false;
                            System.out.println("Please create an account first");
                            MainMenu.printMainMenu();

                        } else {
                            System.out.println("Please enter Y (Yes) or N (No)");
                            loop = true;
                        }
                    } catch (Exception ex) {
                        System.out.println("Please enter Y (Yes) or N (No)");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                hotelResource.getCustomer(addedEmail);

                //reserve a room
                System.out.println("What room would you like to reserve");
                do {
                    try {
                        addedRoomNumber = in.next();
                        addedRoom = hotelResource.getRoom(addedRoomNumber);

                        if (availableRooms.contains(addedRoom)) {
                            System.out.println(hotelResource.bookARoom(addedEmail, addedRoom, addedCheckInDate, addedCheckOutDate));
                            loop = false;
                            MainMenu.printMainMenu();
                        } else {
                            System.out.println("Your input is in Invalid. Please enter the room number");
                        }

                    } catch (Exception e) {
                        System.out.println("Your input is in Invalid. Please enter the room number");
                        in.next();
                        loop = true;

                    }
                } while (loop);

            //show customer reservation
            case 2:
                do {
                    try {
                        System.out.println("Enter Email format: name@domain.com");
                        String mail = in.next();
                        if (pattern.matcher(mail).matches()) {
                            addedEmail = mail;
                            loop = false;
                        } else {
                            System.out.println("Wrong email format.");
                            loop = true;
                        }
                    } catch (Exception exception) {
                        System.out.println("Wrong email format.");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                //print customer's reservation
                for (Reservation reservations : hotelResource.getCustomersReservations(addedEmail) ){
                    System.out.println(reservations.toString());
                }
                MainMenu.printMainMenu();

                //create an account
            case 3:
                do {
                    try {
                        System.out.println("Enter Email format: name@domain.com");
                        String mail = in.next();

                        //check if the email format is right
                        if (pattern.matcher(mail).matches()) {
                            addedEmail = mail;
                            loop = false;
                        } else {
                            System.out.println("Wrong email format.");
                            loop = true;
                        }

                        //check if customer are creating same email address
                        for (Customer customer : hotelResource.getallCustomer()) {

                            if (customer.getEmail().equals(addedEmail)) {
                                System.out.println("You have registered. Please don't register again");
                                loop = true;
                                MainMenu.printMainMenu();
                            } else {
                                loop = false;
                            }
                        }

                    } catch (Exception exception) {
                        System.out.println("Wrong email format.");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                do {
                    try {
                        System.out.println("First Name");
                        addedFirstName = in.next();
                        loop = false;
                    } catch (Exception exception) {
                        System.out.println("Please reenter your first name");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                do {
                    try {
                        System.out.println("Last Name");
                        addedLastName = in.next();
                        loop = false;
                    } catch (Exception exception) {
                        System.out.println("Please reenter your last name");
                        in.next();
                        loop = true;
                    }
                } while (loop);

                hotelResource.createACustomer(addedEmail, addedFirstName, addedLastName);
                System.out.println("You account have been successfully created.");
                MainMenu.printMainMenu();

            case 4:
                AdminMenu.printAdminMenu();

            case 5:
                System.out.println("Thank you for using our system");

        }
    }

}
