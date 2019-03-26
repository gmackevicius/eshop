package lt.bit.eshop.service;

import lt.bit.eshop.config.CustomUserDetails;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.util.Optional;



@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserEntity newUser = new UserEntity();
//        newUser.setUsername("admin");
//        newUser.setPassword(encoder.encode("secret"));
//        newUser.setEnabled(true);
//        this.userRepository.save(newUser);

        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);

        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(optionalUser.get());
    }


    public void createNewUser(UserModel usermodel) {

        UserEntity newUser = new UserEntity(usermodel);

        newUser.setPassword(encoder.encode(newUser.getPassword()));

        this.userRepository.save(newUser);

    }
}
