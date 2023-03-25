package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.DTO.AccountDto;
import pl.Portfolio.bankingapp.DTO.UserDto;
import pl.Portfolio.bankingapp.Exceptions.AccountNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.UserNotFoundException;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Model.Account;
import pl.Portfolio.bankingapp.Services.AccountService;
import pl.Portfolio.bankingapp.Services.UserService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getAll()
    {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id)
    {
        try{
            return ResponseEntity.ok(accountService.getAccountById(id));
        } catch (AccountNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/getbyuserid/{id}")
    public ResponseEntity getByUserId(@PathVariable("id") int id)
    {
        try {
            return ResponseEntity.ok(accountService.getAccountsByOwnerId(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity getByUsername(@PathVariable("username") String username)
    {
        try {
            UserDto user = userService.getUserByUsername(username);
            return ResponseEntity.ok(accountService.getAccountsByOwnerId(user.getId()));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody AccountDto accountDto)
    {
        return ResponseEntity.ok(accountService.saveAccount(accountDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody AccountDto updatedAccountDto)
    {
        try {
            return ResponseEntity.ok(accountService.updateAccount(id, updatedAccountDto));
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/partiallyupdate/{id}")
    public ResponseEntity partiallyUpdate(@PathVariable("id") int id, @RequestBody AccountDto updatedAccountDto)
    {
        try {
            return ResponseEntity.ok(accountService.partiallyUpdateAccount(id, updatedAccountDto));
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

}
