package pl.Portfolio.bankingapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    private int id;
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    private String email;
    private String role;
}
