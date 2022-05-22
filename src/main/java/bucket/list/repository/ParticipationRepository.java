package bucket.list.repository;

import bucket.list.domain.Participation;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation,Integer> {

    @Modifying
    @Transactional
    @Query("update participation p set p.participation_count = p.participation_count +1 where p.participationidx = :participationidx")
    Integer updateCount(@Param("participationidx") Integer participationidx);
}
