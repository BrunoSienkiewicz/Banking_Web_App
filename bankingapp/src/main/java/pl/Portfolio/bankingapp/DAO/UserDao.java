package pl.Portfolio.bankingapp.DAO;

import pl.Portfolio.bankingapp.Model.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    User getByUsername(String username);

    User getByEmail(String email);
}
