package com.c0220i1.group.service.products;

import com.c0220i1.group.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountService extends UserDetailsService {

    Iterable<Account> findAll();
    Optional<Account> findById(Long id);
    void save(Account account);
    void remove(Long id);

}
