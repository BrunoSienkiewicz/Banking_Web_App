package pl.Portfolio.bankingapp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.DTO.LoginDto;
import pl.Portfolio.bankingapp.DTO.UserCreateDto;
import pl.Portfolio.bankingapp.DTO.UserDto;
import pl.Portfolio.bankingapp.Exceptions.EmailAlreadyUsedException;
import pl.Portfolio.bankingapp.Exceptions.InvalidCredentialsException;
import pl.Portfolio.bankingapp.Exceptions.UserNotFoundException;
import pl.Portfolio.bankingapp.Exceptions.UsernameTakenException;
import pl.Portfolio.bankingapp.Model.User;
import pl.Portfolio.bankingapp.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final TokenService tokenService;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.getByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        return userRepository.getByEmail(email) == null;
    }

    public UserDto saveUser(UserCreateDto userDto) throws UsernameTakenException, EmailAlreadyUsedException{

        if (!isUsernameAvailable(userDto.getUsername())) {
            throw new UsernameTakenException("Username " + userDto.getUsername() + " is already taken");
        }

        if (!isEmailAvailable(userDto.getEmail())) {
            throw new EmailAlreadyUsedException("Email " + userDto.getEmail() + " is already used");
        }

        User user = modelMapper.map(userDto, User.class);

        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(modelMapper.map(user, UserDto.class));
        }
        return usersDto;
    }

    public UserDto getUserById(int id) throws  UserNotFoundException{
        User user = userRepository.getById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.getByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByEmail(String email) throws UserNotFoundException{
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return modelMapper.map(user, UserDto.class);
    }

    public int updateUser(int id, UserDto userDto) throws UsernameTakenException, EmailAlreadyUsedException, UserNotFoundException {
        User user = userRepository.getById(id);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!isEmailAvailable(userDto.getEmail()) && !user.getEmail().equals(userDto.getEmail()))
        {
            throw new EmailAlreadyUsedException("Email is already used");
        }

        if (!isUsernameAvailable(userDto.getUsername()) && !user.getUsername().equals(userDto.getUsername()))
        {
            throw new UsernameTakenException("Username is already taken");
        }

        user.setEmail(userDto.getEmail());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getUsername());
        user.setId(id);

        userRepository.update(user);

        return 1;
    }

    public int partialUpdateUser(int id, UserDto userDto) throws UsernameTakenException, EmailAlreadyUsedException, UserNotFoundException {
        User user = userRepository.getById(id);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!isEmailAvailable(userDto.getEmail()) && !user.getEmail().equals(userDto.getEmail()) && userDto.getEmail() != null)
        {
            throw new EmailAlreadyUsedException("Email is already used");
        }

        if (!isUsernameAvailable(userDto.getUsername()) && !user.getUsername().equals(userDto.getUsername()) && userDto.getUsername() != null)
        {
            throw new UsernameTakenException("Username is already taken");
        }

        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getFirst_name() != null) {
            user.setFirst_name(userDto.getFirst_name());
        }
        if (userDto.getLast_name() != null) {
            user.setLast_name(userDto.getLast_name());
        }
        if (userDto.getRole() != null) {
            user.setRole(userDto.getRole());
        }
        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        user.setId(id);

        userRepository.update(user);

        return 1;
    }

    public int deleteUser(int id) throws UserNotFoundException{
        if (userRepository.getById(id) == null)
            throw new UserNotFoundException("User not found");
        return userRepository.delete(id);
    }

    public String login(LoginDto loginDto) throws UserNotFoundException, InvalidCredentialsException {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        User user = userRepository.getByEmail(email);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        if (user.getPassword().equals(password)) {
            String token = tokenService.generateToken(user);
            return token;
        }
        if (!user.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        return tokenService.getUsernameFromToken(token);
    }

    public String getRoleFromToken(String token) {
        return tokenService.getRoleFromToken(token);
    }
}
