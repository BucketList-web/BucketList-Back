package bucket.list.repository;

import bucket.list.domain.Participation;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation,Integer> {

    @Modifying
    @Query("update participation p set p.count = p.count+1 where p.participationidx = :participationidx")
    int updateCount(@Param("participationidx") int participationidx);

//    @Query("select p from participation p order by p.count desc")
//    List<Participation> bestContent();
    List<Participation> findTop3ByOrderByCountDesc();


}
