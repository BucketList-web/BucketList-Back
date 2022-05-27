package bucket.list.repository.Login;

//import spring.basic.demo.domain.Data;
import bucket.list.domain.Community;
import bucket.list.domain.User;

import java.util.List;

public interface LoginRepositoryInterface { // service로 함수 보냄

    User findUserById(String id);
//    List<Community> mypagefindall(String name);
    List<User> findIdAll();
}
