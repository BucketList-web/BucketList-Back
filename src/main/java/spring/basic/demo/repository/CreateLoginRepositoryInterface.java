package spring.basic.demo.repository;

//import spring.basic.demo.domain.Data;
import spring.basic.demo.domain.CreateLogin;

public interface CreateLoginRepositoryInterface { // service로 함수 보냄

    void CreateLoginCreate(CreateLogin m);
//    List<Data> findDataAll();   // 모든 데이터 출력
}
