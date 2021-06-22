package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import ui.MainMenu;

import java.util.*;

public class ReservationService {

    //Singleton Reservationservice, make sure only one object is created
    private static ReservationService reservationService = null;
    private  ReservationService(){
    }

    public  static ReservationService getInstance() {
        if (null == reservationService){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    //room list and reservation list is stored here
    Set<IRoom> roomList = new HashSet<>();
    Set<Reservation> reservationList = new HashSet<>();

    public void addRoom(IRoom room){
        this.roomList.add(room);
    }

    public IRoom getARoom(String roomID){
        IRoom gettedRoom = null;
        for (IRoom room : roomList) {
            if (roomID.equals(room.getRoomNumber())){
                gettedRoom = room;
            }
        }
        return gettedRoom;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        return reservation;
    }

    public Set<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Set<IRoom> availableRoomList = new HashSet<>();

        checkavailableRoom(checkInDate, checkOutDate, availableRoomList);

//        for (Reservation reservation : reservationList){
//            Date checkinDate1 = reservation.getCheckinDate();
//            Date checkoutDate1 = reservation.getCheckOutDate();
//            IRoom reservedRoom = reservation.getRoom();
//            if (isOverlapped(checkInDate, checkOutDate, checkinDate1, checkoutDate1)){
//                availableRoomList.remove(reservedRoom);
//            }
//        }

//        if (availableRoomList.isEmpty()){
//            System.out.println("Recommended Room");
//            Calendar incalendar = Calendar.getInstance();
//            Calendar outcalendar = Calendar.getInstance();
//            incalendar.setTime(checkInDate);
//            outcalendar.setTime(checkOutDate);
//            incalendar.add(Calendar.DATE, 7);
//            outcalendar.add(Calendar.DATE, 7);
//
//            Date recommendCheckinDate = incalendar.getTime();
//            Date recommendCheckoutDate = outcalendar.getTime();
//            System.out.println("Checkin Date: " + recommendCheckinDate + "    Checkout Date:" + recommendCheckoutDate);
//
//            checkavailableRoom(recommendCheckinDate,recommendCheckoutDate, availableRoomList);
//
//        }

        return availableRoomList;
    }

    public Set<Reservation> getCumstomersReservation(Customer customer){
        Set<Reservation> customersreservation = new HashSet<>();
        for (Reservation reservation : reservationList){
            if (reservation.getCustomer().equals(customer)){
                customersreservation.add(reservation);
            }
        }

        if (customersreservation.isEmpty()){
            System.out.println("Sorry, your have no reservation in our Hotel.Please reserve your room first");
        }
        return  customersreservation;
    }

    public void printAllReservation(){
        for (Reservation reservation: reservationList){
            System.out.println(reservation.toString());
        }
    }


    public Set<IRoom> getRoomList(){
        return roomList;
    }

    public  Set<Reservation> getReservationList(){
        return reservationList;
    }

    public static boolean isOverlapped (Date checkinDate1, Date checkoutDate1, Date checkinDate2, Date checkoutDate2) {
        return !checkinDate1.after(checkoutDate2) && !checkinDate2.after(checkoutDate1);
    }

    //check available room during input checkInDate and checkOutDate
    private Set<IRoom> checkavailableRoom(Date checkInDate, Date checkOutDate, Set<IRoom> availableRoomList) {

        availableRoomList.addAll(roomList);

        for (Reservation reservation : reservationList){
            Date checkinDate1 = reservation.getCheckinDate();
            Date checkoutDate1 = reservation.getCheckOutDate();
            IRoom reservedRoom = reservation.getRoom();
            if (isOverlapped(checkInDate, checkOutDate, checkinDate1, checkoutDate1)){
                availableRoomList.remove(reservedRoom);
            }
        }

        return  availableRoomList;
    }
}
