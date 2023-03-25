package pl.Portfolio.bankingapp.DAO;

import pl.Portfolio.bankingapp.Model.Account;

import javax.net.ssl.SSLSession;
import java.util.List;

public interface AccountDao extends Dao<Account> {
    List<Account> findByUserId(int userId);

    Account getByAccountNumber(String accountNumber);
}
