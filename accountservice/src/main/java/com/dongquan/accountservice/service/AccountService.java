
package com.dongquan.accountservice.service;
import com.dongquan.accountservice.data.Account;
import com.dongquan.accountservice.model.AccountDTO;
import com.dongquan.accountservice.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Mono<AccountDTO> createNewAccount(AccountDTO accountDTO) {
        return Mono.just(accountDTO)
                .map(AccountDTO::dtoToEntity)
                .map(account -> {
                    if (account.getId() == null || account.getId().isBlank()) {
                        account.setId(UUID.randomUUID().toString());
                    }
                    return account;
                })
                .flatMap(accountRepository::save)
                .map(AccountDTO::entityToModel)
                .doOnError(error ->
                        log.error("Failed to create account", error));
    }
}