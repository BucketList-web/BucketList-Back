package bucket.list.service.Login;

import bucket.list.domain.CreateLogin;
import bucket.list.domain.User;
import bucket.list.repository.Login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User finduser(String name){
        return userRepository.finduser(name);
    }

    public boolean checkUserIdExist(String user_id){
        String byPhone = userRepository.existUserId(user_id);

        User user = new User();

        if(byPhone ==null){

            user.setUserExistId(false);

            return user.isUserExistId();
        }else{
            user.setUserExistId(true);

            return user.isUserExistId();
        }
    }
}
