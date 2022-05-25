package bucket.list.repository.Login;

//import spring.basic.demo.domain.Data;
import bucket.list.domain.CreateLogin;

import java.util.List;

public interface CreateLoginRepositoryInterface { // service로 함수 보냄

    void CreateLoginCreate(CreateLogin m);
//    List<Data> findDataAll();   // 모든 데이터 출력
}
