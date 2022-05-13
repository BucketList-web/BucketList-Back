package spring.basic.demo.repository;

//import spring.basic.demo.domain.Data;
import spring.basic.demo.domain.Lodging;
import spring.basic.demo.domain.Place;
import spring.basic.demo.domain.Store;

import java.util.List;

public interface BoardRepositoryInterface { // service로 함수 보냄

    void savestore(Store m);
    Store findstoreById(int id);
    List<Store> findstoreAll();

//    void savelodging(Lodging m);
//    Lodging findlodgingById(int id);
//    List<Lodging> findlodgingAll();
//
//    void saveplace(Place m);
//    Place findplaceById(int id);
//    List<Place> findplaceAll();


//    List<Data> findDataAll();   // 모든 데이터 출력
}
