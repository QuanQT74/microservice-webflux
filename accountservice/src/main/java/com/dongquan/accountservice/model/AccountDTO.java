package com.dongquan.accountservice.model;

import com.dongquan.accountservice.data.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String id;

    private  String email;

    private String currency;

    private double balance;

    private double reserved;

    public static AccountDTO entityToModel(Account account){
        return AccountDTO.builder()
                .email(account.getEmail())
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .reserved(account.getReverver())
                .id(account.getId())
                .build();
    }
    public static  Account dtoToEntity(AccountDTO accountDTO){
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setBalance(accountDTO.getBalance());
        account.setEmail(accountDTO.getEmail());
        account.setCurrency(accountDTO.getCurrency());
        account.setReverver(accountDTO.getReserved());
        return account;
    }
}
