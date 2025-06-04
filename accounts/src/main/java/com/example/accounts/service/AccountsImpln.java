package com.example.accounts.service;

import com.example.accounts.constants.AccountsConstants;
import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;
import com.example.accounts.entity.Accounts;
import com.example.accounts.entity.Customer;
import com.example.accounts.exception.CustomerAlreadyExistsException;
import com.example.accounts.exception.ResourceNotFoundException;
import com.example.accounts.mapper.AccountsMapper;
import com.example.accounts.mapper.CustomerMapper;
import com.example.accounts.repo.AccountsRepository;
import com.example.accounts.repo.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsImpln implements IAccountsService{

    @Autowired
    AccountsRepository accountsRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer=CustomerMapper.mapToEntity(customerDto,new Customer());
        Optional<Customer> checkingForMobileNumber=customerRepository.findBymobileNumber(customerDto.getMobileNumber());

        if(checkingForMobileNumber.isPresent())
        {
            throw  new CustomerAlreadyExistsException("Customer already present with the registred number"+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }



    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
         newAccount.setCreatedAt(LocalDateTime.now());
       newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

       Customer customerByMobileNumber= customerRepository.findBymobileNumber(mobileNumber).orElseThrow(
               ()-> new ResourceNotFoundException("customer","mobilenumber",mobileNumber)
       );

        Accounts accountsByCustomerId=accountsRepository.findByCustomerId(customerByMobileNumber.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("accounts","customerid",customerByMobileNumber.getCustomerId().toString())
        );

        CustomerDto customerDto=CustomerMapper.mapToDto(customerByMobileNumber,new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToDto(accountsByCustomerId, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer=customerRepository.findBymobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("customer","delete",mobileNumber)
        );

       customerRepository.deleteById(customer.getCustomerId());
        return true;
    }


}









