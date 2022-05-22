package bucket.list.repository;

import bucket.list.domain.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutRepository extends JpaRepository<About,Integer> {

//    About Save(About about);
//
//    List<About> allContentList();
//
//    About oneContentList(int about_number);
}
