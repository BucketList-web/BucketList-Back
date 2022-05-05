package spring.basic.demo.repository;

import spring.basic.demo.domain.Store;

import java.util.List;

public interface BoardRepositoryInterface {

    void savedata(Store m);
    Store findById(int id);
    List<Store> findAll();
}
