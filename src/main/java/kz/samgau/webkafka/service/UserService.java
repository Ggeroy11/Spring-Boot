package kz.samgau.webkafka.service;

import kz.samgau.webkafka.model.Role;
import kz.samgau.webkafka.model.User;
import kz.samgau.webkafka.repository.RoleRepository;
import kz.samgau.webkafka.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository repository, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.roleRepository = roleRepository;
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public User addNewUser(User user) {
        user.getRoles().forEach(role -> {
            Role addRole = roleRepository.getByName(role.getName());
            if(addRole != null){

            }
        });

        return userRepository.save(user);
    }

    public User updateUser(Long id, String name){
        User user = userRepository.getById(id);
        user.setName(name);
        userRepository.save(user);
        return user;
    }

    public String deleteUser(User user){
        user.getRoles().forEach(user::removeRole);
        userRepository.delete(user);
        return "User deleted";
    }
}
