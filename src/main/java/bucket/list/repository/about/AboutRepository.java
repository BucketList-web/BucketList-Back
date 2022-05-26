package bucket.list.repository.about;

import bucket.list.domain.About;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AboutRepository extends JpaRepository<About,Integer> {

    @Query(value = "select about_writer from about where aboutnumber=?", nativeQuery = true)
    String selectIdSQL(int aboutnumber);
}
