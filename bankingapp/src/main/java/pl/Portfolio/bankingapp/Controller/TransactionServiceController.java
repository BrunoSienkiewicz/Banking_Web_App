package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Repository.TransactionRepository;
import pl.Portfolio.bankingapp.Services.TransactionService;

@RestController
@RequestMapping("/transactionsservice")
public class TransactionServiceController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;
}
