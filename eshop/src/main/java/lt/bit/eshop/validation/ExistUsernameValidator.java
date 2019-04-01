package lt.bit.eshop.validation;

import lt.bit.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistUsernameValidator implements ConstraintValidator<ExistUsername, String> {

   @Autowired
   private UserService userService;

   public void initialize(ExistUsername constraint) {
   }

   public boolean isValid(String username, ConstraintValidatorContext context) {
      return !userService.getUserByUsername(username).isPresent();
   }
}
