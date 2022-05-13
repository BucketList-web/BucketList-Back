package spring.basic.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.basic.demo.domain.Lodging;
import spring.basic.demo.domain.Place;
import spring.basic.demo.domain.Store;
import spring.basic.demo.repository.BoardRepositoryInterface;

import java.util.List;

@Service     //spring bean 사용하여 따로 설정했으므로 삭제해야함
public class BoardService {

    private BoardRepositoryInterface repository;

    @Autowired
    public BoardService(BoardRepositoryInterface repository){         // service에서 repository 연결하는 느낌
        this.repository = repository;                               // 사용자는 repository에 접근 하지 못하고 service까지에만 접근이
        // 가능하도록 구현하기 위하여 service에서 repository를 한번더 감싼 느낌
    }

    // ---------------- 맛집관련 서비스 -----------------------

    public void createStore(Store m){                 // join == (repository)saveMember

        repository.savestore(m);
    }

    public Store showStoreById(int id){       // findMemberBuId == (repository)findById

        return repository.findstoreById(id);
    }

    public List<Store> showStoreAll(){

        return repository.findstoreAll();            // repository에 findAll 함수 호출
    }


    // ---------------- 숙소관련 서비스 -----------------------

//    public void createLodging(Lodging m){                 // join == (repository)saveMember
//
//        repository.savelodging(m);
//    }
//
//    public Lodging showLodgingById(int id){       // findMemberBuId == (repository)findById
//
//        return repository.findlodgingById(id);
//    }
//
//    public List<Lodging> showLodgingAll(){
//
//        return repository.findlodgingAll();            // repository에 findAll 함수 호출
//    }
//
//
//    // ---------------- 장소 관련 서비스 -----------------------
//    public void createPlace(Place m){                 // join == (repository)saveMember
//
//        repository.saveplace(m);
//    }
//
//    public Place showPlaceById(int id){       // findMemberBuId == (repository)findById
//
//        return repository.findplaceById(id);
//    }
//
//    public List<Place> showPlaceAll(){
//
//        return repository.findplaceAll();            // repository에 findAll 함수 호출
//    }
}
