package com.example.cardealer.services.customer;

import com.example.cardealer.entities.customer.Customer;
import com.example.cardealer.entities.customer.CustomersWithSalesDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    List<CustomersWithSalesDto> selectAllOrderedByBirthdate();
}
