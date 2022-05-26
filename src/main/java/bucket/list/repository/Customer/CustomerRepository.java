package bucket.list.repository.Customer;

import bucket.list.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    @Query(value = "select customerwriter from customer where customeridx=?", nativeQuery = true)
    String selectIdSQL(int customerIdx);

}
