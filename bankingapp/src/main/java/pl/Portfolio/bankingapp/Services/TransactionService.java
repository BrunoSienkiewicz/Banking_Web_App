package pl.Portfolio.bankingapp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.Model.DTO.TransactionDto;
import pl.Portfolio.bankingapp.Model.DTO.TransferDto;
import pl.Portfolio.bankingapp.Exceptions.AccountNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.TransactionNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.UserNotFoundException;
import pl.Portfolio.bankingapp.Model.Base.User;
import pl.Portfolio.bankingapp.Repository.AccountRepository;
import pl.Portfolio.bankingapp.Repository.TransactionRepository;
import pl.Portfolio.bankingapp.Exceptions.InsufficientFundsException;
import pl.Portfolio.bankingapp.Model.Base.Account;
import pl.Portfolio.bankingapp.Model.Base.Transaction;
import pl.Portfolio.bankingapp.Repository.UserRepository;

import java.sql.Timestamp;
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
        transactionDto.setDate(transaction.getTransaction_date().toString());
        transactionDto.setType(transaction.getTransaction_type().toUpperCase());
        return transactionDto;
    }

    private Transaction mapTransactionDtoToTransaction(TransactionDto transactionDto)
    {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setAccount_id(accountRepository.getByAccountNumber(transactionDto.getAccount_number()).getId());
        transaction.setTransaction_date(Timestamp.valueOf(transactionDto.getDate() + " 00:00:00.000"));
        return transaction;
    }

    @Autowired
    public TransactionService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> transferMoney(TransferDto transferDto) throws InsufficientFundsException, AccountNotFoundException {
        int fromAccountId = accountRepository.getByAccountNumber(transferDto.getFromAccount()).getId();
        int toAccountId = accountRepository.getByAccountNumber(transferDto.getToAccount()).getId();
        int amount = transferDto.getAmount();

        Account fromAccount = accountRepository.getById(fromAccountId);
        Account toAccount = accountRepository.getById(toAccountId);

        if (fromAccount == null || toAccount == null)
            throw new AccountNotFoundException("Account not found");

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
        Transaction fromTransaction = new Transaction(Timestamp.valueOf(now), "TRANSFER", amount, fromAccountId);
        Transaction toTransaction = new Transaction(Timestamp.valueOf(now), "DEPOSIT", amount, toAccountId);
        List<Transaction> transactions = List.of(fromTransaction, toTransaction);
        transactionRepository.save(transactions);

        List<TransactionDto> transactionsDto = new ArrayList<>();
        transactionsDto.add(mapTransactionToTransactionDto(fromTransaction));
        transactionsDto.add(mapTransactionToTransactionDto(toTransaction));
        return transactionsDto;
    }

    public List<TransactionDto> getTransactionsByAccountId(int accountId) throws TransactionNotFoundException{
        List<Transaction> transactions = transactionRepository.findByAccountId(accountId);

        if (transactions == null)
            throw new TransactionNotFoundException("No transactions found");

        List<TransactionDto> transactionsDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionsDto.add(mapTransactionToTransactionDto(transaction));
        }

        return transactionsDto;
    }

    public List<TransactionDto> getTransactionsByUserId(int userId) throws UserNotFoundException, AccountNotFoundException, TransactionNotFoundException {
        User user = userRepository.getById(userId);

        if (user == null)
            throw new UserNotFoundException("User not found");

        List<Account> accounts = accountRepository.findByUserId(userId);

        if (accounts == null)
            throw new AccountNotFoundException("No accounts found");

        List<TransactionDto> transactionsDto = new ArrayList<>();
        for (Account account : accounts) {
            List<Transaction> transactions = transactionRepository.findByAccountId(account.getId());

            if (transactions == null)
                throw new TransactionNotFoundException("No transactions found");

            for (Transaction transaction : transactions) {
                transactionsDto.add(mapTransactionToTransactionDto(transaction));
            }
        }

        return transactionsDto;
    }

    public List<TransactionDto> getAllTransactions() throws TransactionNotFoundException{
        List<Transaction> transactions = transactionRepository.getAll();

        if (transactions == null)
            throw new TransactionNotFoundException("No transactions found");

        List<TransactionDto> transactionsDto = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionsDto.add(mapTransactionToTransactionDto(transaction));
        }

        return transactionsDto;
    }

    public TransactionDto getTransactionById(int id) throws TransactionNotFoundException {
        Transaction transaction = transactionRepository.getById(id);

        if (transaction == null)
            throw new TransactionNotFoundException("Transaction not found");

        return mapTransactionToTransactionDto(transaction);
    }

    public int saveTransactions(List<TransactionDto> transactionsDto) {
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionDto transactionDto : transactionsDto) {
            Transaction transaction = mapTransactionDtoToTransaction(transactionDto);
            transactions.add(transaction);
        }

        return transactionRepository.save(transactions);
    }

    public int deleteTransactionById(int id) throws TransactionNotFoundException{
        Transaction transaction = transactionRepository.getById(id);
        if (transaction == null)
            throw new TransactionNotFoundException("Transaction not found");

        return transactionRepository.delete(id);
    }

    public List<TransactionDto> getTransactionsByUsername(String username) {
        User user = userRepository.getByUsername(username);
        if (user == null)
            throw new UserNotFoundException("User not found");

        return getTransactionsByUserId(user.getId());
    }
}
