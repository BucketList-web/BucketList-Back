package spring.basic.demo.repository;

import spring.basic.demo.domain.Lodging;
import spring.basic.demo.domain.Store;

import java.util.List;

public interface BoardRepositoryInterface { // service로 함수 보냄

    void savestore(Store m);
    Store findstoreById(int id);
    List<Store> findstoreAll();

    void savelodging(Lodging m);
    Lodging findlodgingById(int id);
    List<Lodging> findlodgingAll();
}
