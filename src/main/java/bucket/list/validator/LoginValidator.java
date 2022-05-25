/*package bucket.list.validator;


import bucket.list.domain.Login;
import bucket.list.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//비밀번호와 비밀번호 확인이 일치한지 확인하기위한 validator 선언
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Login login = (Login) target;
//        String beanName = errors.getObjectName();



        if(login.getId().equals(user.getUser_pw2())==false){
            errors.rejectValue("user_pw", "NotEquals", "비밀번호가 일치하지 않습니다");
            errors.rejectValue("user_pw2", "NotEquals", "비밀번호가 일치하지 않습니다");
        }
    }
}
*/