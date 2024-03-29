package pl.Portfolio.bankingapp.Model.Base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int id;
    private Timestamp transaction_date;
    private String transaction_type;
    private int amount;
    private int account_id;

    public Transaction(Timestamp transaction_date, String transaction_type, int amount, int account_id) {
        this.transaction_date = transaction_date;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.account_id = account_id;
    }
}
