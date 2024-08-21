package com.azizsaparniyazov.services;

import com.azizsaparniyazov.models.Customer;
import com.azizsaparniyazov.services.utilServices.JsonReader;
import com.azizsaparniyazov.services.utilServices.JsonWriter;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService {
    private final String PATH = "src\\Customers.json";

    public Customer addCustomer(String customerId, String userId, String customerName) {
        List<Customer> customers = read();
        if (!hasCustomer(customerId, userId)){
            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setUserId(userId);
            customer.setName(customerName);
            customers.add(customer);
            write(customers);
            return customer;
        }
        throw new RuntimeException("Customer already exists");
    }

    public List<Customer> showMyCustomers(String userId) {
        return read().stream().filter(customer -> customer.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    private boolean hasCustomer(String customerId, String userId){
        return read().stream().anyMatch(customer -> customer.getId().equals(customerId)
                && customer.getUserId().equals(userId));
    }




    public List<Customer> read() {
        return JsonReader.readGson(PATH, new TypeReference<List<Customer>>() {
        });
    }

    public void write(List<Customer> buckets) {
        JsonWriter.writeGson(buckets, PATH);
    }
}
