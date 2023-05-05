package pl.Portfolio.bankingapp.Model.Base;

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
}
