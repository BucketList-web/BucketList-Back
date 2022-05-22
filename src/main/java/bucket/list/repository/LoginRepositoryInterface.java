package bucket.list.repository;

//import spring.basic.demo.domain.Data;
import bucket.list.domain.User;

import java.util.List;

public interface LoginRepositoryInterface { // service로 함수 보냄

    User findUserById(String id);
//    List<Data> findDataAll();   // 모든 데이터 출력
}
