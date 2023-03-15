package pl.Portfolio.bankingapp.DAO;

import pl.Portfolio.bankingapp.Model.Account;

import java.util.List;

public interface AccountDao extends Dao<Account> {
    List<Account> findByUserId(int userId);
}
