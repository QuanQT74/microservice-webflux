package com.dongquan.accountservice.repository;

import com.dongquan.accountservice.data.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<Account,String> {

}
