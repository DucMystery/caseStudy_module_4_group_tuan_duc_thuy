package com.c0220i1.group.service.products.Impl;

import com.c0220i1.group.model.Account;
import com.c0220i1.group.model.Role;
import com.c0220i1.group.repository.account.AccountRepository;
import com.c0220i1.group.service.products.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        List<GrantedAuthority> authorities = account.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
        UserDetails userDetails = new User(
                account.getUsername(),
                account.getPassword(),
                authorities
        );
        return userDetails;
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

    @Override
    public Account findByName(String username) {
        Account account = accountRepository.findByUsername(username);
        return account;
    }
}
