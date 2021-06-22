package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.Date;
import java.util.Set;

public class HotelResource {

    ReservationService reservationService = ReservationService.getInstance();
    CustomerService customerService = CustomerService.getInstance();

    public void getCustomer(String email){
        customerService.getCustomer(email);

    }

    public void createACustomer(String email, String firstName, String lastName){
        customerService.addCustomer(email, firstName, lastName);

    }

    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date ceckOutDate){
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, ceckOutDate);
    }

    public Set<Reservation> getCustomersReservations(String customerEmail){
        return reservationService.getCumstomersReservation(customerService.getCustomer(customerEmail));
    }

    public Set<IRoom> findARoom(Date checkIn, Date checkOut){
        return reservationService.findRooms(checkIn, checkOut);
    }

    public Set<Customer> getallCustomer() {
        return customerService.getAllCumstomers();
    }
}
