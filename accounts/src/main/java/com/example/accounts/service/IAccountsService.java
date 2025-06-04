package com.example.accounts.service;

import com.example.accounts.dto.AccountsDto;
import com.example.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    public boolean deleteAccount(String mobileNumber);
}
