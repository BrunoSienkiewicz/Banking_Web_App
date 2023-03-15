package pl.Portfolio.bankingapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private String account_number;
    private String account_type;
    private int balance;
    private int user_id;

}
