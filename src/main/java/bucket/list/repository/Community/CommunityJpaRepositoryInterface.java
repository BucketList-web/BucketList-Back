package bucket.list.repository.Community;

//import spring.basic.demo.domain.Data;


import bucket.list.domain.Community;
import bucket.list.domain.CreateLogin;
import bucket.list.domain.Search;
import bucket.list.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommunityJpaRepositoryInterface extends JpaRepository<Community,Object> { // service로 함수 보냄

   // List<Community> findTop10ByOrderByDesc();

    @Query(value = "select *from community where communitywriter=?", nativeQuery = true)
    List<Community> selectAllSQL(String name);

    @Query(value = "select communitywriter from community where communityid=?", nativeQuery = true)
    String selectIdSQL(int id);


    List<Community> findByCommunitytagContaining(Search search);
//    List<Community> findTop3ByNameOrderByIdDesc(String name);

}
