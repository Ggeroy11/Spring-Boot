package kz.samgau.webkafka.controller;

import kz.samgau.webkafka.exception.UserException;
import kz.samgau.webkafka.model.Role;
import kz.samgau.webkafka.model.User;
import kz.samgau.webkafka.model.dto.UserDto;
import kz.samgau.webkafka.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = convertUserToUserDto(userService.getUserById(id));
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        throw new UserException("User not found by id = " + id);
    }

    @PostMapping(value = "/add-new-user", produces = "application/json")
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto) {
        User user = convertDtoToUser(userDto);
        if (user != null) {
            UserDto userDtoResponse = convertUserToUserDto(userService.addNewUser(user));
            return new ResponseEntity<>(userDtoResponse, HttpStatus.OK);
        }
        throw new UserException("user not added ");
    }

    @PutMapping(value = "/update-user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Param("name") String name) {
        UserDto userDto = convertUserToUserDto(userService.updateUser(id, name));
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        throw  new UserException("Could not update because not found");
    }

    @DeleteMapping(value = "/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto){
        String message = userService.deleteUser(convertDtoToUser(userDto));
        if(message != null){
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        throw new UserException("User not found");
    }

    private User convertDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
