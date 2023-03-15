package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.Repository.UserRepository;
import pl.Portfolio.bankingapp.Model.User;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getall")
    public List<User> getAll()
    {
        return userRepository.getAll();
    }

    @GetMapping("/get/{id}")
    public User getById(@PathVariable("id") int id)
    {
        return userRepository.getById(id);
    }

    @GetMapping("/get/{username}")
    public User getByUsername(@PathVariable("username") String username)
    {
        return userRepository.getByUsername(username);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<User> users)
    {
        return userRepository.save(users);
    }

    @PutMapping("/update/{id}")
    public int update(@PathVariable("id") int id, @RequestBody User updatedUser)
    {
        User user = userRepository.getById(id);

        if (user != null)
        {
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());

            userRepository.update(user);

            return 1;
        }else
        {
            return -1;
        }
    }

    @PatchMapping("/partiallyupdate/{id}")
    public int patch(@PathVariable("id") int id, @RequestBody User updatedUser)
    {
        User user = userRepository.getById(id);

        if (user != null)
        {
            if (updatedUser.getUsername() != null)
            {
                user.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getPassword() != null)
            {
                user.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getRole() != null)
            {
                user.setRole(updatedUser.getRole());
            }

            userRepository.update(user);

            return 1;
        }else
        {
            return -1;
        }
    }
}
