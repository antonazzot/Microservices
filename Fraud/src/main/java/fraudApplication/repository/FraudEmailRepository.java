package fraudApplication.repository;

import fraudApplication.model.FrauderEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FraudEmailRepository extends JpaRepository<FrauderEmail, Integer> {
    public Optional<FrauderEmail> findFrauderByEmail (String email);
}
