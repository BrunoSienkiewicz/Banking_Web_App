package pl.Portfolio.bankingapp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.DTO.AccountDto;
import pl.Portfolio.bankingapp.Exceptions.NegativeBalanceException;
import pl.Portfolio.bankingapp.Model.Account;
import pl.Portfolio.bankingapp.Model.User;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    private String getUserNameById(int id) {
        User user = userRepository.getById(id);
        return user.getUsername();
    }

    private AccountDto mapAccountToAccountDto(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        accountDto.setOwner_username(getUserNameById(account.getUser_id()));
        return accountDto;
    }

    private Account mapAccountDtoToAccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account.setUser_id(userRepository.getByUsername(accountDto.getOwner_username()).getId());
        return account;
    }

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public int saveAccounts(List<AccountDto> accountsDto) {
        List<Account> accounts = new ArrayList<>();
        for (AccountDto accountDto : accountsDto) {
            accounts.add(mapAccountDtoToAccount(accountDto));
        }
        return accountRepository.save(accounts);
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.getAll();
        List<AccountDto> accountsDto = new ArrayList<>();
        for (Account account : accounts) {
            accountsDto.add(mapAccountToAccountDto(account));
        }
        return accountsDto;
    }

    public AccountDto getAccountById(int id) {
        Account account = accountRepository.getById(id);
        return mapAccountToAccountDto(account);
    }

    public int updateAccount(int id, AccountDto accountDto) throws NegativeBalanceException{
        Account account = accountRepository.getById(id);

        if (accountDto.getBalance() < 0) {
            throw new NegativeBalanceException("Balance cannot be negative");
        }

        if (account != null) {
            account.setAccount_number(accountDto.getAccount_number());
            account.setAccount_type(accountDto.getAccount_type());
            account.setBalance(accountDto.getBalance());

            accountRepository.update(account);

            return 1;
        } else {
            return -1;
        }
    }

    public int partiallyUpdateAccount(int id, AccountDto accountDto) throws NegativeBalanceException {
        Account account = accountRepository.getById(id);

        if (accountDto.getBalance() < 0) {
            throw new NegativeBalanceException("Balance cannot be negative");
        }

        if (account != null) {
            if (accountDto.getAccount_number() != null) {
                account.setAccount_number(accountDto.getAccount_number());
            }
            if (accountDto.getAccount_type() != null) {
                account.setAccount_type(accountDto.getAccount_type());
            }
            if (accountDto.getBalance() >= 0) {
                account.setBalance(accountDto.getBalance());
            }

            accountRepository.update(account);

            return 1;
        } else {
            return -1;
        }
    }

    public int deleteAccount(int id) {
        return accountRepository.delete(id);
    }

    public List<AccountDto> getAccountsByOwnerId(int ownerId) {
        List<Account> accounts = accountRepository.findByUserId(ownerId);

        List<AccountDto> accountsDto = new ArrayList<>();
        for (Account account : accounts) {
            accountsDto.add(mapAccountToAccountDto(account));
        }
        return accountsDto;
    }
}
