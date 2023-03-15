package pl.Portfolio.bankingapp.Services;

import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Repository.TransactionRepository;
import pl.Portfolio.bankingapp.Exceptions.InsufficientFundsException;
import pl.Portfolio.bankingapp.Model.Account;
import pl.Portfolio.bankingapp.Model.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public void transferMoney(int fromAccountId, int toAccountId, int amount) throws InsufficientFundsException {
        Account fromAccount = accountRepository.getById(fromAccountId);
        Account toAccount = accountRepository.getById(toAccountId);

        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in account " + fromAccount.getAccount_number());
        }

        int fromBalance = fromAccount.getBalance() - amount;
        int toBalance = toAccount.getBalance() + amount;
        fromAccount.setBalance(fromBalance);
        toAccount.setBalance(toBalance);

        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);

        LocalDateTime now = LocalDateTime.now();
        Transaction fromTransaction = new Transaction(now.toString(), "WITHDRAWAL", amount, fromAccountId);
        Transaction toTransaction = new Transaction(now.toString(), "DEPOSIT", amount, toAccountId);
        List<Transaction> transactions = List.of(fromTransaction, toTransaction);
        transactionRepository.save(transactions);
    }

}
