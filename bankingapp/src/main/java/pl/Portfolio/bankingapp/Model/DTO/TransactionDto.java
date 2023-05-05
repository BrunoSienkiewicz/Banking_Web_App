package pl.Portfolio.bankingapp.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private int id;
    private String date;
    private String type;
    private int amount;
    private String account_number;

}
