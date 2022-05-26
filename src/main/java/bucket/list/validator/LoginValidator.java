package bucket.list.validator;


import bucket.list.domain.Login;
import bucket.list.domain.User;
import bucket.list.service.Community.CommunityService;
import bucket.list.service.Login.LoginService;
import bucket.list.service.Login.UserService;
import bucket.list.service.Participation.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//비밀번호와 비밀번호 확인이 일치한지 확인하기위한 validator 선언
public class LoginValidator implements Validator {

    private final LoginService service;

    @Autowired
    public LoginValidator(LoginService service){         // controller와 service 연결하는 느낌
        this.service = service;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Login.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Login login = (Login) target;
        String beanName = errors.getObjectName();

        Login m = new Login();
        User userdata = service.showUserById(m.getId());
        String dbpw = userdata.getUser_pw();

        if(login.getId().equals(dbpw)==false){
            errors.rejectValue("pw", "NotEquals", "비밀번호가 일치하지 않습니다");
        }
    }
}


