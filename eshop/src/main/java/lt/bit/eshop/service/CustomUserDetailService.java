package lt.bit.eshop.service;

import lt.bit.eshop.config.CustomUserDetails;
import lt.bit.eshop.entity.Authority;
import lt.bit.eshop.entity.RoleEntity;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Encoder;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUser = this.userRepository.findByUsername(username);

        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException(username);
        }

        Set<RoleEntity> roles = optionalUser.get().getRoles();

        Set<GrantedAuthority> authorities =  roles.stream()
                .map(r -> r.getAuthorities())
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        roles.forEach(r -> {
            StringBuilder roleName = new StringBuilder("ROLE_");
            roleName.append(r.getName());
            authorities.add(new Authority(roleName.toString()));
        });

        return new CustomUserDetails(optionalUser.get(), authorities);
    }


    public void createNewUser(UserModel usermodel) {

        UserEntity newUser = new UserEntity(usermodel);

        newUser.setPassword(encoder.encode(newUser.getPassword()));

        this.userRepository.save(newUser);

    }

    public List<UserModel> getAllUsers() {

        List<UserEntity> users = (List<UserEntity>) this.userRepository.findAll();



        return users.stream().map(UserModel::new).collect(Collectors.toList());
    }
}
