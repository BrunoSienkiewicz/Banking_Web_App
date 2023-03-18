package pl.Portfolio.bankingapp.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.Portfolio.bankingapp.DAO.UserDao;
import pl.Portfolio.bankingapp.Model.User;

import java.util.List;

@Repository
public class UserRepository implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAll()
    {
        return jdbcTemplate.query("SELECT * FROM banking_db.users",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    public User getById(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM banking_db.users WHERE id = ? ",
                BeanPropertyRowMapper.newInstance(User.class), id);
    }

    public int save(List<User> users)
    {
        users.forEach(user -> jdbcTemplate
                .update("INSERT INTO banking_db.users(username, password, first_name, last_name, email, role) VALUES(?, ?, ?, ?, ?, ?)",
                        user.getUsername(), user.getPassword(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getRole()));
        return 1;
    }

    public int update(User user)
    {
        return jdbcTemplate.update("UPDATE banking_db.users SET username=?, password=?, first_name=?, last_name=?, email=?, role=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getRole(), user.getId());
    }

    public int delete(int id)
    {
        return jdbcTemplate.update("DELETE FROM banking_db.users WHERE id=?", id);
    }

    public User getByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM banking_db.users WHERE username = ? ",
                BeanPropertyRowMapper.newInstance(User.class), username);
    }

    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM banking_db.users WHERE email = ? ",
                BeanPropertyRowMapper.newInstance(User.class), email);
    }
}
