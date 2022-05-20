package bucket.list.repository;

import bucket.list.domain.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User exist(String user_id);

}
