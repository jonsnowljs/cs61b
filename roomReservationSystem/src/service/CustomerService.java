package service;

import model.Customer;
import ui.AdminMenu;
import ui.MainMenu;

import java.util.HashSet;
import java.util.Set;

public class CustomerService {
    //Singleton customer service.
    private static CustomerService customerService = null;

    private  CustomerService(){

    }
    public  static CustomerService getInstance() {
        if (null == customerService){
            customerService = new CustomerService();
        }
        return customerService;
    }

    //customer data stored here
    Set<Customer> customerList = new HashSet<>();

    public void addCustomer(String email, String firstName, String lastName) {
        Customer newcustomer = new Customer(firstName, lastName, email);
        customerList.add(newcustomer);
    }

    public Customer getCustomer(String customerEmail) {
        Customer gettedCustomer = null;
        for (Customer customer1 : customerList) {
            if (customer1.getEmail().equals(customerEmail)) {
                gettedCustomer = customer1;
            }
        }

        if (gettedCustomer == null){
            System.out.println("You are not our customer, please register first");
            MainMenu.printMainMenu();
        }

        return gettedCustomer;
    }

    public Set<Customer> getAllCumstomers() {
        return customerList;
    }
}
