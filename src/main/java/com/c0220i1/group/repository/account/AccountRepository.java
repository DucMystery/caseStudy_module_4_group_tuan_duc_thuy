package com.c0220i1.group.repository.account;

import com.c0220i1.group.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
}
