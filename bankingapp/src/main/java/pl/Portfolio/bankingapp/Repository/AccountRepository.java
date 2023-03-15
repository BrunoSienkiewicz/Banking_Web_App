package pl.Portfolio.bankingapp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.Portfolio.bankingapp.DAO.AccountDao;
import pl.Portfolio.bankingapp.Model.Account;

import java.util.List;

@Repository
public class AccountRepository implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> getAll()
    {
        return jdbcTemplate.query("SELECT id, account_number, account_type, balance, user_id FROM banking_db.accounts",
                BeanPropertyRowMapper.newInstance(Account.class));
    }

    @Override
    public Account getById(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM banking_db.accounts WHERE id = ? ",
                BeanPropertyRowMapper.newInstance(Account.class), id);
    }

    @Override
    public List<Account> findByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM banking_db.accounts WHERE user_id = ? ",
                BeanPropertyRowMapper.newInstance(Account.class), userId);
    }

    @Override
    public int save(List<Account> accounts)
    {
        accounts.forEach(account -> jdbcTemplate
                .update("INSERT INTO banking_db.accounts(account_number, account_type, balance, user_id) VALUES (?, ?, ?, ?)",
                        account.getAccount_number(), account.getAccount_type(), account.getBalance(), account.getUser_id()));
        return 1;
    }

    @Override
    public int update(Account account)
    {
        return jdbcTemplate.update("UPDATE banking_db.accounts SET account_number=?, account_type=?, balance=?, user_id=? WHERE id=?",
                account.getAccount_number(), account.getAccount_type(), account.getBalance(), account.getUser_id());
    }

    @Override
    public int delete(int id)
    {
        return jdbcTemplate.update("DELETE FROM banking_db.accounts WHERE id=?", id);
    }
}
