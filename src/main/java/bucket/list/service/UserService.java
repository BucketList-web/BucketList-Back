package bucket.list.service;

import bucket.list.domain.User;
import bucket.list.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public boolean exist(String user_id) {

        User user = userRepository.exist(user_id);

//        User user = new User();

        if (user.getUser_phone() == null) {

//            throw new IllegalStateException("이미 존재하는 ID 입니다");

//            user.setUser_exist(false);
            return true;

        } else {
//            user.setUser_exist(true);

//            return user.isUser_exist();

            return false;

        }
    }
}
