package com.example.demo.dto;

import java.util.List;

public class JobRequest {
    private List<CustomerDto> customers;

    public List<CustomerDto> getCustomers() { return customers; }
    public void setCustomers(List<CustomerDto> customers) { this.customers = customers; }
}
