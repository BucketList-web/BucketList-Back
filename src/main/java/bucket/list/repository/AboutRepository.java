package bucket.list.repository;

import bucket.list.domain.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutRepository extends JpaRepository<About,Integer> {

    @Query("select a from About a order by a.aboutnumber asc")
    List<About> newContent();
}
