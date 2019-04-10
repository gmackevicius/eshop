package lt.bit.eshop.services;

import lt.bit.eshop.entity.UserEntity;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.repository.UserRepository;
import lt.bit.eshop.service.UserService;
import lt.bit.eshop.validation.exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void mockData() {
        UserEntity user = new UserEntity();
        user.setName("Elephant");

        Optional<UserEntity> optional = Optional.of(user);

        when(userRepository.findByUsername("admin")).thenReturn(optional);
    }

    @Test
    public void shouldReturnOptionalUserModel() throws UserNotFoundException {
//        assertTrue(userService.getUserByUsername("admin") instanceof UserModel);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundException() throws UserNotFoundException {
        userService.getUserByUsername("admin");
    }
}