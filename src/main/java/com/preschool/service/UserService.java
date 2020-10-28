package com.preschool.service;

import com.preschool.exeption.PreschoolNotFoundExection;
import com.preschool.model.User;
import com.preschool.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @param model
     * @return
     */
    public User createUser(User model)
    {
        User newUser = new User(model.getUserName(),model.getPassword());
        userRepository.save(newUser);
        return newUser;
    }

    /**
     *
     * @param id
     * @return
     */
    public User getUserById(int id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new PreschoolNotFoundExection(id));
    }

    /**
     *
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
