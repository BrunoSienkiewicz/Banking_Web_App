package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.DTO.TransactionDto;
import pl.Portfolio.bankingapp.Model.Transaction;
import pl.Portfolio.bankingapp.Services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/getall")
    public List<TransactionDto> getAll()
    {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable("id") int id)
    {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/getbyaccountid/{id}")
    public List<TransactionDto> getByAccountId(@PathVariable("id") int id)
    {
        return transactionService.getTransactionsByAccountId(id);
    }

    @GetMapping("/getbyuserid/{id}")
    public List<TransactionDto> getByUserId(@PathVariable("id") int id)
    {
        return transactionService.getTransactionsByUserId(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<TransactionDto> transactions)
    {
        return transactionService.saveTransactions(transactions);
    }

    @PostMapping("/transfer/{fromAccountId}/{toAccountId}/{amount}")
    public List<TransactionDto> transfer(@PathVariable("fromAccountId") int fromAccountId, @PathVariable("toAccountId") int toAccountId, @PathVariable("amount") int amount)
    {
        return transactionService.transferMoney(fromAccountId, toAccountId, amount);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id)
    {
        return transactionService.deleteTransactionById(id);
    }

}
