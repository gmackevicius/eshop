package lt.bit.eshop.validation;

import lt.bit.eshop.form.UserModel;
import org.apache.catalina.User;
import org.springframework.security.core.parameters.P;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcherValidator implements ConstraintValidator<PasswordMatches, Object> {
   public void initialize(PasswordMatches constraint) {
   }

   public boolean isValid(Object obj, ConstraintValidatorContext context) {
    final UserModel user = (UserModel) obj;

    boolean isValid = user.getPassword().equals(user.getMatchPassword());

    if(!isValid) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
        ).addPropertyNode("matchPassword")
         .addConstraintViolation();
    }

    return isValid;
   }
}
