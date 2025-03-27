package app.TFGWordle.security.service;

import app.TFGWordle.security.entity.MainUser;
import app.TFGWordle.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainUserService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.getByUserName(name);
        if (userOptional.isPresent()) {
            return MainUser.build(userOptional.get());
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre: " + name);
        }
    }
}
