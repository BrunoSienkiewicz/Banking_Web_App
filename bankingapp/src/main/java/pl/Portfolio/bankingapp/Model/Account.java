package pl.Portfolio.bankingapp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String account_number;
    private String account_type;
    private int balance;
    private int user_id;

    public Account(String account_number, String account_type, int balance, int user_id) {
        this.account_number = account_number;
        this.account_type = account_type;
        this.balance = balance;
        this.user_id = user_id;
    }
}
