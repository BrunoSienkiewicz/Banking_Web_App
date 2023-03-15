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
        return jdbcTemplate.query("SELECT id, username, password, full_name, role FROM banking_db.users",
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
                .update("INSERT INTO banking_db.users(username, password, full_name, role) VALUES (?, ?, ?, ?)",
                        user.getUsername(), user.getPassword(), user.getFull_name(), user.getRole()));
        return 1;
    }

    public int update(User user)
    {
        return jdbcTemplate.update("UPDATE banking_db.users SET username=?, password=?, full_name=?, role=? WHERE id=?",
                user.getUsername(), user.getPassword(), user.getFull_name(), user.getRole());
    }

    public int delete(int id)
    {
        return jdbcTemplate.update("DELETE FROM banking_db.users WHERE id=?", id);
    }

    public User getByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM banking_db.users WHERE username = ? ",
                BeanPropertyRowMapper.newInstance(User.class), username);
    }
}
