package pl.Portfolio.bankingapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferDto {
    private String fromAccount;
    private String toAccount;
    private int amount;
}
