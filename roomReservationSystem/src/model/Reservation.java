package model;

import java.util.Date;

public class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkinDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkinDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkOutDate = checkOutDate;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public IRoom getRoom(){
        return room;
    }

    public Customer getCustomer(){
        return customer;
    }

    @Override
    public String toString() {
        return  "Reservation\n" +
                "Customer: " + customer + "\n" +
                "Room: " + room + "\n" +
                "Checkin Date: " + checkinDate + "\n" +
                "CheckOut Date: " + checkOutDate+ "\n" ;
    }

    //override equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Reservation reservation = (Reservation) o;
        return customer.equals(reservation.customer)
                && (room.equals(reservation.room))
                && (checkinDate.equals(reservation.checkinDate))
                && (checkOutDate.equals(reservation.checkOutDate));

    }

    @Override
    public int hashCode() {
        int result = 31 * customer.hashCode();
        result = 31 * result + room.hashCode();
        result = 31 * result + checkinDate.hashCode();
        result = 31 * result + checkOutDate.hashCode();
        return result;
    }
}
