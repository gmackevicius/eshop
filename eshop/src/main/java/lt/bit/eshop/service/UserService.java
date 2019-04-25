package lt.bit.eshop.service;

import lt.bit.eshop.config.CustomUserDetails;
import lt.bit.eshop.entity.*;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.CartRepository;
import lt.bit.eshop.repository.RoleRepository;
import lt.bit.eshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CartRepository cartRepository;

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

        CartEntity cart = new CartEntity();
        Set<CartItem> emptyCart = new HashSet<>();
        cart.setCartItems(emptyCart);
        newUser.setCart(cart);
        List<Order> emptyOrderList = new ArrayList<>();
        newUser.setOrders(emptyOrderList);

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

    public UserEntity getUserByUsername(String username) {
        UserEntity user = null;
        if(userRepository.findByUsername(username).isPresent()) {
            user = userRepository.findByUsername(username).get();
        }
            return user;
    }

    public UserEntity getCurrentUser() {

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userToReturn = null;

        if(!(user instanceof AnonymousAuthenticationToken)) {
            if(userRepository.findByUsername(user.getName()).isPresent()){
                userToReturn = userRepository.findByUsername(user.getName()).get();
            }
        } else {
            UserEntity anonymousUser = new UserEntity();
            anonymousUser.setUsername("anonymousUser");
            anonymousUser.setCart(new CartEntity());
            anonymousUser.getCart().setCartItems(new HashSet<>());
            userToReturn = anonymousUser;
        }

        return userToReturn;
    }

    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }


}
