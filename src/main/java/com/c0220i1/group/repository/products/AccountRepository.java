package com.c0220i1.group.repository.products;

import com.c0220i1.group.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository extends PagingAndSortingRepository<Account,Long> {
    Account findAccountByUsername(String username);
}
