package bucket.list.repository.Login;

import bucket.list.domain.CreateLogin;
import bucket.list.domain.User;

public interface UserRepository {

    User save(User user);
    User finduser(String name);
    String existUserId(String user_id);
}
