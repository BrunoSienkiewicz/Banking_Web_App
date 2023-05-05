package pl.Portfolio.bankingapp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.Model.DTO.AccountDto;
import pl.Portfolio.bankingapp.Exceptions.AccountNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.NegativeBalanceException;
import pl.Portfolio.bankingapp.Exceptions.UserNotFoundException;
import pl.Portfolio.bankingapp.Model.Base.Account;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper = new ModelMapper();

    private String getUserNameById(int id) throws UserNotFoundException {
        if (userRepository.getById(id) == null)
            throw new UserNotFoundException("User with id " + id + " not found");

        return userRepository.getById(id).getUsername();
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

    public int saveAccount(AccountDto accountDto) {
        Account account = mapAccountDtoToAccount(accountDto);

        return accountRepository.save(account);
    }

    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.getAll();
        List<AccountDto> accountsDto = new ArrayList<>();
        for (Account account : accounts) {
            accountsDto.add(mapAccountToAccountDto(account));
        }
        return accountsDto;
    }

    public AccountDto getAccountById(int id) throws AccountNotFoundException {
        Account account = accountRepository.getById(id);

        if (account == null)
            throw new AccountNotFoundException("Account with id " + id + " not found");

        return mapAccountToAccountDto(account);
    }

    public int updateAccount(int id, AccountDto accountDto) throws NegativeBalanceException, AccountNotFoundException{
        Account account = accountRepository.getById(id);

        if (account == null)
            throw new AccountNotFoundException("Account with id " + id + " not found");

        if (accountDto.getBalance() < 0) {
            throw new NegativeBalanceException("Balance cannot be negative");
        }

        account.setAccount_number(accountDto.getAccount_number());
        account.setAccount_type(accountDto.getAccount_type());
        account.setBalance(accountDto.getBalance());

        accountRepository.update(account);

        return 1;
    }

    public int partiallyUpdateAccount(int id, AccountDto accountDto) throws NegativeBalanceException, AccountNotFoundException {
        Account account = accountRepository.getById(id);

        if (account == null)
            throw new AccountNotFoundException("Account with id " + id + " not found");

        if (accountDto.getBalance() < 0) {
            throw new NegativeBalanceException("Balance cannot be negative");
        }

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
    }

    public int deleteAccount(int id) {
        if (accountRepository.getById(id) == null)
            throw new AccountNotFoundException("Account with id " + id + " not found");

        return accountRepository.delete(id);
    }

    public List<AccountDto> getAccountsByOwnerId(int ownerId) throws UserNotFoundException, AccountNotFoundException{
        List<Account> accounts = accountRepository.findByUserId(ownerId);

        if (userRepository.getById(ownerId) == null)
            throw new UserNotFoundException("User with id " + ownerId + " not found");

        if (accounts == null)
            throw new AccountNotFoundException("Account with owner id " + ownerId + " not found");

        List<AccountDto> accountsDto = new ArrayList<>();
        for (Account account : accounts) {
            accountsDto.add(mapAccountToAccountDto(account));
        }
        return accountsDto;
    }
}
