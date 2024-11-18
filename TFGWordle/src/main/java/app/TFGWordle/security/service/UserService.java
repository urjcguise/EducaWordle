package app.TFGWordle.security.service;

import app.TFGWordle.security.entity.User;
import app.TFGWordle.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
    public Optional<User> getById(Long id) {
       return userRepository.findById(id);
    }
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUsername(userName);
    }
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
    public void save(User user) {
        userRepository.save(user);
    }
}
