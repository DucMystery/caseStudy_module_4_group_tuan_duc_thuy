package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.Account;
import com.c0220i1.group.repository.products.AccountRepository;
import com.c0220i1.group.service.products.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
  public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }
}