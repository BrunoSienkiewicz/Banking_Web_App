package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Model.Account;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/getall")
    public List<Account> getAll()
    {
        return accountRepository.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable("id") int id)
    {
        return accountRepository.getById(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<Account> accounts)
    {
        return accountRepository.save(accounts);
    }

    @PutMapping("/update/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Account updatedAccount)
    {
        Account account = accountRepository.getById(id);

        if (account != null)
        {
            account.setAccount_type(updatedAccount.getAccount_type());
            account.setAccount_number(updatedAccount.getAccount_number());
            account.setBalance(updatedAccount.getBalance());
            account.setUser_id(updatedAccount.getUser_id());

            accountRepository.update(account);

            return 1;
        }else
        {
            return -1;
        }
    }

    @PatchMapping("/partiallyupdate/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Account updatedAccount)
    {
        Account account = accountRepository.getById(id);

        if (account != null)
        {
            if (updatedAccount.getAccount_number() != null) {
                account.setAccount_number(updatedAccount.getAccount_number());
            }
            if (updatedAccount.getAccount_type() != null) {
                account.setAccount_type(updatedAccount.getAccount_type());
            }
            if (updatedAccount.getBalance() >= 0) {
                account.setBalance(updatedAccount.getBalance());
            }
            if (updatedAccount.getUser_id() > 0) {
                account.setUser_id(updatedAccount.getUser_id());
            }

            accountRepository.update(account);
            return 1;
        }else
        {
            return -1;
        }
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id)
    {
        return accountRepository.delete(id);
    }
}
