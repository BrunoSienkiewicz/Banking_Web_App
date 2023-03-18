package pl.Portfolio.bankingapp.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.Portfolio.bankingapp.DTO.UserDto;
import pl.Portfolio.bankingapp.Exceptions.EmailAlreadyUsedException;
import pl.Portfolio.bankingapp.Exceptions.UsernameTakenException;
import pl.Portfolio.bankingapp.Model.User;
import pl.Portfolio.bankingapp.Repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.getByUsername(username) == null;
    }

    public boolean isEmailAvailable(String email) {
        return userRepository.getByEmail(email) == null;
    }

    public int saveUsers(List<UserDto> usersDto) {
        List<User> users = new ArrayList<>();
        for (UserDto userDto : usersDto) {
            users.add(modelMapper.map(userDto, User.class));
        }
        return userRepository.save(users);
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.getAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(modelMapper.map(user, UserDto.class));
        }
        return usersDto;
    }

    public UserDto getUserById(int id) {
        User user = userRepository.getById(id);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByUsername(String username) {
        User user = userRepository.getByUsername(username);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.getByEmail(email);
        return modelMapper.map(user, UserDto.class);
    }

    public int updateUser(int id, UserDto userDto) throws UsernameTakenException, EmailAlreadyUsedException {
        User user = userRepository.getById(id);

        if (!isEmailAvailable(userDto.getEmail()))
        {
            throw new EmailAlreadyUsedException("Email is already used");
        }

        if (!isUsernameAvailable(userDto.getUsername()))
        {
            throw new UsernameTakenException("Username is already taken");
        }

        if (user != null) {
            user.setEmail(userDto.getEmail());
            user.setFirst_name(userDto.getFirst_name());
            user.setLast_name(userDto.getLast_name());
            user.setRole(userDto.getRole());
            user.setUsername(userDto.getUsername());

            userRepository.update(user);

            return 1;
        }else
        {
            return -1;
        }
    }

    public int partialUpdateUser(int id, UserDto userDto) throws UsernameTakenException, EmailAlreadyUsedException {
        User user = userRepository.getById(id);

        if (!isEmailAvailable(userDto.getEmail()))
        {
            throw new EmailAlreadyUsedException("Email is already used");
        }

        if (!isUsernameAvailable(userDto.getUsername()))
        {
            throw new UsernameTakenException("Username is already taken");
        }

        if (user != null) {
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

            userRepository.update(user);

            return 1;
        }else
        {
            return -1;
        }
    }

    public int deleteUser(int id) {
        return userRepository.delete(id);
    }
}
