package pl.Portfolio.bankingapp.DAO;

import pl.Portfolio.bankingapp.Model.Base.User;

public interface UserDao extends Dao<User>{
    User getByUsername(String username);

    User getByEmail(String email);
}
