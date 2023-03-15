package pl.Portfolio.bankingapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String full_name;
    private String role;

    public User(String username, String password, String full_name, String role) {
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.role = role;
    }
}
