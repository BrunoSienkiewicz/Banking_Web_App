package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.Repository.TransactionRepository;
import pl.Portfolio.bankingapp.Model.Transaction;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/getall")
    public List<Transaction> getAll()
    {
        return transactionRepository.getAll();
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable("id") int id)
    {
        return transactionRepository.getById(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<Transaction> transactions)
    {
        return transactionRepository.save(transactions);
    }

    @PutMapping("/update/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Transaction updatedTransaction)
    {
        Transaction transaction = transactionRepository.getById(id);

        if (transaction != null)
        {
            transaction.setTransaction_type(updatedTransaction.getTransaction_type());
            transaction.setTransaction_date(updatedTransaction.getTransaction_date());
            transaction.setAmount(updatedTransaction.getAmount());
            transaction.setAccount_id(updatedTransaction.getAccount_id());

            transactionRepository.update(transaction);

            return 1;
        }else
        {
            return -1;
        }
    }

    @PatchMapping("/partiallyupdate/{id}")
    public int patch(@PathVariable("id") int id, @RequestBody Transaction updatedTransaction)
    {
        Transaction transaction = transactionRepository.getById(id);

        if (transaction != null)
        {
            if (updatedTransaction.getTransaction_type() != null)
            {
                transaction.setTransaction_type(updatedTransaction.getTransaction_type());
            }
            if (updatedTransaction.getTransaction_date() != null)
            {
                transaction.setTransaction_date(updatedTransaction.getTransaction_date());
            }
            if (updatedTransaction.getAmount() != 0)
            {
                transaction.setAmount(updatedTransaction.getAmount());
            }
            if (updatedTransaction.getAccount_id() != 0)
            {
                transaction.setAccount_id(updatedTransaction.getAccount_id());
            }

            transactionRepository.update(transaction);

            return 1;
        }else
        {
            return -1;
        }
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id)
    {
        return transactionRepository.delete(id);
    }

}
