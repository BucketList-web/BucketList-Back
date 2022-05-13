package spring.basic.demo.repository;

//import spring.basic.demo.domain.Data;
import spring.basic.demo.domain.Community;

import java.util.List;

public interface BoardRepositoryInterface { // service로 함수 보냄

    void writecommunity(Community m);
    Community findcommunityById(int id);
    List<Community> findcommunityAll();


//    List<Data> findDataAll();   // 모든 데이터 출력
}
