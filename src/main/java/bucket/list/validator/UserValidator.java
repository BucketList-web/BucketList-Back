package bucket.list.validator;


import bucket.list.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
//비밀번호와 비밀번호 확인이 일치한지 확인하기위한 validator 선언
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;
//        String beanName = errors.getObjectName();

        if(user.getUser_pw().equals(user.getUser_pw2())==false){
            errors.rejectValue("user_pw", "NotEquals", "비밀번호가 일치하지 않습니다");
        }
    }
}
