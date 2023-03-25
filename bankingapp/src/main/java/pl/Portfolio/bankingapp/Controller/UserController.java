package pl.Portfolio.bankingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.Portfolio.bankingapp.DTO.LoginDto;
import pl.Portfolio.bankingapp.DTO.UserCreateDto;
import pl.Portfolio.bankingapp.DTO.UserDto;
import pl.Portfolio.bankingapp.Exceptions.EmailAlreadyUsedException;
import pl.Portfolio.bankingapp.Exceptions.InvalidCredentialsException;
import pl.Portfolio.bankingapp.Exceptions.UserNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.UsernameTakenException;
import pl.Portfolio.bankingapp.Services.UserService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getall")
    public ResponseEntity getAll()
    {
        List <UserDto> allUsers =  userService.getAllUsers();

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable("id") int id)
    {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity getByUsername(@PathVariable("username") String username)
    {
        try {
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/getbyemail/{email}")
    public ResponseEntity getByEmail(@PathVariable("email") String email)
    {
        try {
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody UserCreateDto user)
    {
        try {
            return ResponseEntity.ok(userService.saveUser(user));
        } catch (UsernameTakenException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody UserDto updatedUser)
    {
        try {
            return ResponseEntity.ok(userService.updateUser(id, updatedUser));
        }catch (UserNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UsernameTakenException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("/partiallyupdate/{id}")
    public ResponseEntity patch(@PathVariable("id") int id, @RequestBody UserDto updatedUser)
    {
        try {
            return ResponseEntity.ok(userService.partialUpdateUser(id, updatedUser));
        } catch (UserNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (UsernameTakenException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int id)
    {
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (UserNotFoundException e)
        {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto user)
    {
        try {
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (InvalidCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
}
