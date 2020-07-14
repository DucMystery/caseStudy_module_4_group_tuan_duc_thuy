package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.Account;
import com.c0220i1.group.repository.account.AccountRepository;
import com.c0220i1.group.service.products.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null)
            throw new UsernameNotFoundException(username);
        return new CustomUserDetailsImpl(account);
    }


    @Override
    public Iterable<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void remove(Long id) {

    }
}
