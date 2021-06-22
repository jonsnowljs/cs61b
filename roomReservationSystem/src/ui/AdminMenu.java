package ui;

import api.AdminResource;
import model.*;
import service.ReservationService;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AdminMenu {
    public static void printAdminMenu() {
        String split = "-----------------------------------------------------";

        AdminResource adminResource = new AdminResource();

        //print the admin menu
        System.out.println("\nAdminMenu");
        System.out.println(split);
        System.out.printf("%s%n%s%n%s%n%s%n%s%n", "1. See all Customers", "2. See all Rooms", "3. See all Reservations"
                , "4. Add a Room", "5. Add Test Data");
        System.out.println("6. Back to Main Menu\n");
        System.out.println(split);
        System.out.println("Please select a number for the menu option");

        //receive command from customers
        Scanner in = new Scanner(System.in);
        int command = 0;

        //check the command
        try {
            command = in.nextInt();
            if (command > 6 || command < 1){
                System.out.println("You input is wrong. Please select a number from 1 to 6");
                AdminMenu.printAdminMenu();
            }
        }catch (Exception e){
            System.out.println("You input is wrong. Please select a number from 1 to 6");
            AdminMenu.printAdminMenu();
        }


        switch (command) {

            //print all customers
            case 1:
                Set<Customer> allCustomers = adminResource.getAllCustomers();

                for (Customer customer : allCustomers) {
                    System.out.println(customer.toString());
                    //System.out.printf("%n First Name: %-10s Last Name: %-10s     Email: %s%n",  customer.getFirstName(), customer.getLastName() , customer.getEmail());
                }

                AdminMenu.printAdminMenu();

            //print all rooms
            case 2:
                Set<IRoom> allRooms = adminResource.getAllRooms();

                for (IRoom room : allRooms) {
                    //System.out.printf("%nRoom Number: %-6s %s bed Room   Price: $%4.1f%n",room.getRoomNumber(), room.getRoomType(), room.getRoomPrice());
                    System.out.println(room.toString());
                }

                AdminMenu.printAdminMenu();

            //print all reservation
            case 3:
                adminResource.displayAllReservations();
                AdminMenu.printAdminMenu();

            //Add a room
            case 4:
                String addedRoomNumber = null;
                Double addedPrice = null;
                RoomType addedEnumeration = null;
                int addedIndex;
                boolean caseloop = false;
                IRoom addedRoom;
                boolean loop = false;
                Set<IRoom> addedrooms = new HashSet<>();

                do {
                    do {
                        try {
                            System.out.println("Enter room number");
                            addedRoomNumber = in.next();

                            //check if you are inputing same room number
                            for(IRoom room : adminResource.getAllRooms()){
                                if (room.getRoomNumber().equals( addedRoomNumber)){
                                    System.out.println("You have entered this room. Please choose another room to input.");
                                    loop = true;
                                    break;
                                }
                                else {
                                    loop = false;
                                }
                            }
                        } catch (Exception ex) {
                            System.out.println("You input is illegal. Please write a number");
                            in.next();
                            loop = true;
                        }
                    } while (loop);

                    do {
                        System.out.println("Enter price per night");
                        try {
                            addedPrice = in.nextDouble();
                            loop = false;
                        } catch (Exception ex) {
                            System.out.println("You input is illegal. Please write a price");
                            in.next();
                            loop = true;
                        }
                    } while (loop);


                    do {
                        System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                        try {
                            addedIndex = in.nextInt();
                            if (addedIndex == 1) {
                                addedEnumeration = RoomType.SINGLE;
                                loop = false;
                            } else if (addedIndex == 2) {
                                addedEnumeration = RoomType.DOUBLE;
                                loop = false;
                            } else {
                                System.out.println("You input is illegal. Please write a price");
                                loop = true;
                            }
                        } catch (Exception ex) {
                            System.out.println("You input is illegal. Please write a price");
                            in.next();
                            loop = true;
                        }

                    } while (loop);

                    addedRoom = new Room(addedRoomNumber, addedPrice, addedEnumeration);
                    addedrooms.add(addedRoom);
                    adminResource.addRoom(addedrooms);

                    do {
                        System.out.println("Would you like to add another room y/n");
                        try {
                            String yn = in.next();
                            if (yn.equals("y") || yn.equals("Y") || yn.equals("Yes")) {
                                caseloop = true;
                                loop = false;
                            } else if (yn.equals("N") || yn.equals("n") || yn.equals("No")) {
                                caseloop = false;
                                loop = false;
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

                } while (caseloop);



                AdminMenu.printAdminMenu();


            case 5:
                System.out.println("To be developed");
                AdminMenu.printAdminMenu();

            case 6:
                MainMenu.printMainMenu();
        }

    }
}