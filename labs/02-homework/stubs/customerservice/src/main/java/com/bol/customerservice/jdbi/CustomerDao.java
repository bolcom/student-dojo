package com.bol.customerservice.jdbi;

import com.bol.customerservice.api.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CustomerDao {
    private final List<Customer> customers = new ArrayList<>();
    private final Random random = new Random();

    public CustomerDao() {
        initializeStubCustomers();
    }

    private void initializeStubCustomers() {
        customers.add(new Customer(100000L, "Mario", "Mario", "mario@mario.com"));
        customers.add(new Customer(300005L, "Luigi", "Mario", "luigi@mario.com"));
        customers.add(new Customer(500015L, "Princess", "Peaches", "princesspeachconspiracy@nintendo.com"));
        customers.add(new Customer(700020L, "Bob", "Saget", "icantgetnosleep@faithless.com"));
        customers.add(new Customer(900025L, "Meg", "Ryan", "sleeplessin@seattle.com"));
    }

    public Optional<Customer> getCustomer(Long customerNumber) {
        return randomlyFail(customers.stream()
                .filter(customer -> customer.getCustomerNumber().equals(customerNumber))
                .findFirst());
    }

    public List<Customer> getCustomers() {
        return randomlyFail(new ArrayList<>(customers));
    }

    private <T> T randomlyFail(T t) {
        int behaviourIndex = random.nextInt(10);
        // 20% chance the call fails
        if (behaviourIndex < 2) {
            throw new IllegalStateException("Unable to perform method.");
        }
        // 50% chance the call takes longer then expected
        if (behaviourIndex < 5) {
            try {
                Thread.sleep(behaviourIndex * 5000);
            } catch (InterruptedException e) {
                throw new IllegalStateException("Unable to perform method.");
            }
        }
        // 80% chance that what is asked for is actually returned.
        return t;
    }
}
