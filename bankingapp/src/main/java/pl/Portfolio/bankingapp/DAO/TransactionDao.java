package pl.Portfolio.bankingapp.DAO;

import pl.Portfolio.bankingapp.Model.Transaction;

import java.util.List;

public interface TransactionDao extends Dao<Transaction> {
    List<Transaction> findByAccountId(int accountId);
}
