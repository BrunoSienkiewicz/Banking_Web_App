package pl.Portfolio.bankingapp.DAO;

import pl.Portfolio.bankingapp.Model.Base.Transaction;

import java.util.List;

public interface TransactionDao extends Dao<Transaction> {
    List<Transaction> findByAccountId(int accountId);

    int save(Transaction transaction);

    int save(List<Transaction> transactions);
}
