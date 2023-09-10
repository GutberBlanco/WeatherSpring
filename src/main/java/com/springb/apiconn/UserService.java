package com.springb.apiconn;

import com.springb.apiconn.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springb.apiconn.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users createuser(Users users){
        return userRepository.save(users);
    }

    public Users getuserById(Long id){
        Optional<Users> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
