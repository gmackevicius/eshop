package lt.bit.eshop.service;

import lt.bit.eshop.config.CustomUserDetails;
import lt.bit.eshop.entity.CartEntity;
import lt.bit.eshop.entity.CartItem;
import lt.bit.eshop.entity.RoleEntity;
import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.RoleRepository;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    private Optional<UserEntity> userOptional;


    public UserModel getById(Long id) {

        userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return new UserModel(userOptional.get());
        }

        return null; // dead end
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

    public void giveRole(Long id, List<Long> ids) {
        UserEntity user = null;
        userOptional = userRepository.findById(id);
        Iterable<RoleEntity> roles =  roleRepository.findAllById(ids);
        Set<RoleEntity> rolesToSet = new HashSet<>();
        roles.forEach(rolesToSet::add);

        if(userOptional.isPresent()) {
            user = userOptional.get();
        }
        user.setRoles(rolesToSet);

        userRepository.save(user);
    }

    public UserEntity getUserByUsername(String username) { // ar reikia iskart verst i model?
        UserEntity user;
        if(username != "anonymousUser") {
            user = userRepository.findByUsername(username).get();
            return user;
        } else {
            UserEntity anonymousUser = new UserEntity();
            anonymousUser.setUsername("anonymousUser");
            return anonymousUser;
        }

    }

    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }


}
