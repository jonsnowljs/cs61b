package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName, lastName, email;

    private final String regex = "^(.+)@(.+).(.+)";
    private final Pattern pattern = Pattern.compile(regex);
    boolean loop = false;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    @Override
    public String toString() {
        return "FirstName: " + firstName + "    " +
                "LastName: " + lastName + "    " +
                "Email: " + email;
    }


    //override equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return firstName.equals(customer.firstName)
                && (lastName.equals(customer.lastName)
                && email.equals(customer.email));

    }

    @Override
    public int hashCode() {
        int result = 31 * lastName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}