package bucket.list.repository.Login;

import bucket.list.domain.CreateLogin;
import bucket.list.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from userdata where user_id=?", nativeQuery = true)
    User finduser(String user_id);

    @Query(value = "select user_phone from userdata where user_id=?", nativeQuery = true)
    String existUserId(String user_id);


//    User save(User user);
//    User finduser(String name);
//    String existUserId(String user_id);
}
