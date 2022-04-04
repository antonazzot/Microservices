package fraudApplication.repository;

import fraudApplication.model.Frauder;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;

public interface FraudRepository extends JpaRepository<Frauder, Integer> {

    Optional<Frauder> findFrauderBycustomerId(Integer customerId);
}
