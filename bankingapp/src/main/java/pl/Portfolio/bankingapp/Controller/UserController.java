package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.DTO.UserDto;
import pl.Portfolio.bankingapp.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getall")
    public List<UserDto> getAll()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable("id") int id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/get/{username}")
    public UserDto getByUsername(@PathVariable("username") String username)
    {
        return userService.getUserByUsername(username);
    }

    @GetMapping("/get/{email}")
    public UserDto getByEmail(@PathVariable("email") String email)
    {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<UserDto> users)
    {
        return userService.saveUsers(users);
    }

    @PutMapping("/update/{id}")
    public int update(@PathVariable("id") int id, @RequestBody UserDto updatedUser)
    {
        return userService.updateUser(id, updatedUser);
    }

    @PatchMapping("/partiallyupdate/{id}")
    public int patch(@PathVariable("id") int id, @RequestBody UserDto updatedUser)
    {
        return userService.partialUpdateUser(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id)
    {
        return userService.deleteUser(id);
    }
}
