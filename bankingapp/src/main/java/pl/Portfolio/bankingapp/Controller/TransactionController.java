package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.Model.DTO.TransactionDto;
import pl.Portfolio.bankingapp.Model.DTO.TransferDto;
import pl.Portfolio.bankingapp.Exceptions.AccountNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.InsufficientFundsException;
import pl.Portfolio.bankingapp.Exceptions.TransactionNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.UserNotFoundException;
import pl.Portfolio.bankingapp.Services.TransactionService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity getAll()
    {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") int id)
    {
        try {
            return ResponseEntity.ok(transactionService.getTransactionById(id));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity getByAccountId(@PathVariable("id") int id)
    {
        try {
            return ResponseEntity.ok(transactionService.getTransactionsByAccountId(id));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity getByUserId(@PathVariable("id") int id)
    {
        try {
            return ResponseEntity.ok(transactionService.getTransactionsByUserId(id));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UserNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (AccountNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity getByUsername(@RequestParam("username") String username)
    {
        try {
            return ResponseEntity.ok(transactionService.getTransactionsByUsername(username));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UserNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (AccountNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody List<TransactionDto> transactions)
    {
        return ResponseEntity.ok(transactionService.saveTransactions(transactions));
    }

    @PostMapping("/transfer")
    public ResponseEntity transfer(@RequestBody TransferDto transferDto)
    {
        try {
            return ResponseEntity.ok(transactionService.transferMoney(transferDto));
        } catch (AccountNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (InsufficientFundsException e)
        {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id)
    {
        return ResponseEntity.ok(transactionService.deleteTransactionById(id));
    }

}
