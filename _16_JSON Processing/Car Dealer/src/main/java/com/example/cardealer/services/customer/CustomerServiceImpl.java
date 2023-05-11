package com.example.cardealer.services.customer;

import com.example.cardealer.entities.customer.Customer;
import com.example.cardealer.entities.customer.CustomersWithSalesDto;
import com.example.cardealer.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomersWithSalesDto> selectAllOrderedByBirthdate() {
        ModelMapper mapper = new ModelMapper();
        List<Customer> allOrderByBirthDateAsc = this.customerRepository.getByOrderByBirthDateAsc();

        return Arrays.stream(mapper.map(allOrderByBirthDateAsc, CustomersWithSalesDto[].class))
//                .sorted((a,b) -> {
//                    if(a.getBirthDate().equals(b.getBirthDate())){
//                       return Boolean.compare(a.isYoungDriver(),b.isYoungDriver());
//                    }
//                    return 0;
//                })
                .toList();
    }
}
