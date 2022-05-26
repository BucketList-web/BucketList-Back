package bucket.list.service.Login;

import bucket.list.domain.CreateLogin;
import bucket.list.domain.User;
import bucket.list.repository.Login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(User user) {
        User save = userRepository.save(user);
        return save;
    }

    public User finduser(String name){
        return userRepository.finduser(name);
    }
//    public String exist(String user_id) {
//
//        if(user)
//    }
}
