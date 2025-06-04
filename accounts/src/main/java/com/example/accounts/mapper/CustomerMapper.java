package com.example.accounts.mapper;

import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Customer;

public class CustomerMapper {

    //convert entity to dto
        public static CustomerDto mapToDto(Customer customer,CustomerDto customerDto)
    {
       customerDto.setName(customer.getName());
       customerDto.setEmail(customer.getEmail());
       customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    //convert dto to entity
    public static Customer mapToEntity(CustomerDto customerDto,Customer customer)
    {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
