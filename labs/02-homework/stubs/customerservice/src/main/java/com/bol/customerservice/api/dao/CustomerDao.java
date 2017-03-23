package com.bol.customerservice.api.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bol.customerservice.api.Customer;

public class CustomerDao {

    private final List<Customer> customers;

    public CustomerDao() {
        customers = new ArrayList<>();
        customers.add(new Customer(100000L, "Mario", "Mario", "mario@mario.com"));
        customers.add(new Customer(300005L, "Luigi", "Mario", "luigi@mario.com"));
        customers.add(new Customer(500015L, "Princess", "Peaches", "princesspeachconspiracy@nintendo.com"));
        customers.add(new Customer(700020L, "Bob", "Saget", "icantgetnosleep@faithless.com"));
        customers.add(new Customer(900025L, "Meg", "Ryan", "sleeplessin@seattle.com"));
    }

    public Optional<Customer> getCustomer(Long customerNumber) {
        return customers.stream()
                .filter(customer -> customer.getCustomerNumber().equals(customerNumber))
                .findFirst();
    }

    public List<Customer> getCustomers() {
        return new ArrayList<>(customers);
    }
}
