package pl.Portfolio.bankingapp.DTO;

import pl.Portfolio.bankingapp.Model.Account;

public class Mapper {
        public static AccountDto mapToAccountDto(Account account) {
            return new AccountDto(account.getAccount_number(), account.getAccount_type(), account.getBalance(), account.getUser_id());
        }

        public static Account mapToAccount(AccountDto accountDto)
        {
            return new Account(accountDto.getAccount_number(), accountDto.getAccount_type(), accountDto.getBalance(), accountDto.getUser_id());
        }
}
