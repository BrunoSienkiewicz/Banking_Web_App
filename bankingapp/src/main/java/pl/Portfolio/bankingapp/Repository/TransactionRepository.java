package pl.Portfolio.bankingapp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.Portfolio.bankingapp.DAO.TransactionDao;
import pl.Portfolio.bankingapp.Model.Transaction;

import java.util.List;

@Repository
public class TransactionRepository implements TransactionDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Transaction> getAll()
    {
        return jdbcTemplate.query("SELECT id, transaction_type, amount, account_id FROM banking_db.transactions",
                BeanPropertyRowMapper.newInstance(Transaction.class));
    }

    @Override
    public Transaction getById(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM banking_db.transactions WHERE id = ? ",
                BeanPropertyRowMapper.newInstance(Transaction.class), id);
    }

    @Override
    public List<Transaction> findByAccountId(int accountId) {
        return jdbcTemplate.query("SELECT * FROM banking_db.transactions WHERE account_id = ? ",
                BeanPropertyRowMapper.newInstance(Transaction.class), accountId);
    }

    @Override
    public int save(List<Transaction> transactions)
    {
        transactions.forEach(transaction -> jdbcTemplate
                .update("INSERT INTO banking_db.transactions(transaction_type, amount, account_id) VALUES (?, ?, ?)",
                        transaction.getTransaction_type(), transaction.getAmount(), transaction.getAccount_id()));
        return 1;
    }

    @Override
    public int update(Transaction transaction)
    {
        return jdbcTemplate.update("UPDATE banking_db.transactions SET transaction_type=?, amount=?, account_id=? WHERE id=?",
                transaction.getTransaction_type(), transaction.getAmount(), transaction.getAccount_id());
    }

    @Override
    public int delete(int id)
    {
        return jdbcTemplate.update("DELETE FROM banking_db.transactions WHERE id=?", id);
    }
}
