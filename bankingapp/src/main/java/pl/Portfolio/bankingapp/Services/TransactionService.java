package pl.Portfolio.bankingapp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.DTO.TransactionDto;
import pl.Portfolio.bankingapp.Model.User;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Repository.TransactionRepository;
import pl.Portfolio.bankingapp.Exceptions.InsufficientFundsException;
import pl.Portfolio.bankingapp.Model.Account;
import pl.Portfolio.bankingapp.Model.Transaction;
import pl.Portfolio.bankingapp.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private String getAccountNumberById(int id) {
        Account account = accountRepository.getById(id);
        return account.getAccount_number();
    }

    private TransactionDto mapTransactionToTransactionDto(Transaction transaction) {
        TransactionDto transactionDto = modelMapper.map(transaction, TransactionDto.class);
        transactionDto.setAccount_number(getAccountNumberById(transaction.getAccount_id()));
        return transactionDto;
    }

    @Autowired
    public TransactionService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> transferMoney(int fromAccountId, int toAccountId, int amount) throws InsufficientFundsException, IllegalArgumentException {
        Account fromAccount = accountRepository.getById(fromAccountId);
        Account toAccount = accountRepository.getById(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Account not found");
        }

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
        Transaction fromTransaction = new Transaction(now.toString(), "TRANSACTION", amount, fromAccountId);
        Transaction toTransaction = new Transaction(now.toString(), "DEPOSIT", amount, toAccountId);
        List<Transaction> transactions = List.of(fromTransaction, toTransaction);
        transactionRepository.save(transactions);

        List<TransactionDto> transactionsDto = new ArrayList<>();
        transactionsDto.add(mapTransactionToTransactionDto(fromTransaction));
        transactionsDto.add(mapTransactionToTransactionDto(toTransaction));
        return transactionsDto;
    }

    public List<TransactionDto> getTransactionsByAccountId(int accountId) throws IllegalArgumentException{
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        if (transactions == null) {
            throw new IllegalArgumentException("No transactions found");
        }

        List<TransactionDto> transactionsDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionsDto.add(mapTransactionToTransactionDto(transaction));
        }

        return transactionsDto;
    }

    public List<TransactionDto> getTransactionsByUserId(int userId) throws IllegalArgumentException{
        User user = userRepository.getById(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        List<Account> accounts = accountRepository.findByUserId(userId);

        List<TransactionDto> transactionsDto = new ArrayList<>();
        for(Account account : accounts) {
            List<Transaction> transactions = transactionRepository.findByAccountId(account.getId());
            for (Transaction transaction : transactions) {
                transactionsDto.add(mapTransactionToTransactionDto(transaction));
            }
        }

        return transactionsDto;
    }

    public List<TransactionDto> getAllTransactions() throws IllegalArgumentException{
        List<Transaction> transactions = transactionRepository.getAll();

        if (transactions == null) {
            throw new IllegalArgumentException("No transactions found");
        }

        List<TransactionDto> transactionsDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionsDto.add(mapTransactionToTransactionDto(transaction));
        }

        return transactionsDto;
    }

    public TransactionDto getTransactionById(int id) throws IllegalArgumentException{
        Transaction transaction = transactionRepository.getById(id);

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction not found");
        }

        return mapTransactionToTransactionDto(transaction);
    }

    public int saveTransactions(List<TransactionDto> transactionsDto) {
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionDto transactionDto : transactionsDto) {
            Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
            transactions.add(transaction);
        }
        return transactionRepository.save(transactions);
    }

    public int deleteTransactionById(int id) throws IllegalArgumentException{
        Transaction transaction = transactionRepository.getById(id);

        if (transaction == null) {
            throw new IllegalArgumentException("Transaction not found");
        }

        return transactionRepository.delete(id);
    }

}
