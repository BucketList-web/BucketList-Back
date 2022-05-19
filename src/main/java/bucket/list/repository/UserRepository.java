package bucket.list.repository;

import bucket.list.domain.User;

public interface UserRepository {

    User save(User user);

    String exist(String user_id);
}
