package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.Set;

public class AdminResource {
    ReservationService reservationService = ReservationService.getInstance();
    CustomerService customerService = CustomerService.getInstance();

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void addRoom(Set<IRoom> rooms) {
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }


    public Set<IRoom> getAllRooms(){
        return reservationService.getRoomList();

    }

    public Set<Customer> getAllCustomers(){
        return customerService.getAllCumstomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservation();
    }
}
