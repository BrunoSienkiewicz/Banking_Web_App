package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.DTO.AccountDto;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Model.Account;
import pl.Portfolio.bankingapp.Services.AccountService;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/getall")
    public List<AccountDto> getAll()
    {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable("id") int id)
    {
        return accountService.getAccountById(id);
    }

    @GetMapping("/getbyuserid/{id}")
    public List<AccountDto> getByUserId(@PathVariable("id") int id)
    {
        return accountService.getAccountsByOwnerId(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<AccountDto> accountsDto)
    {
        return accountService.saveAccounts(accountsDto);
    }

    @PutMapping("/update/{id}")
    public int update(@PathVariable("id") int id, @RequestBody AccountDto updatedAccountDto)
    {
        return accountService.updateAccount(id, updatedAccountDto);
    }

    @PatchMapping("/partiallyupdate/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody AccountDto updatedAccountDto)
    {
        return accountService.partiallyUpdateAccount(id, updatedAccountDto);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id)
    {
        return accountService.deleteAccount(id);
    }

}
