package fraudApplication.service;

import fraudApplication.model.FrauderEmail;
import fraudApplication.repository.FraudEmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FraudEmailService {
    private final FraudEmailRepository fraudEmailRepository;

    public boolean isFraudEmail (String email) {
        Optional<FrauderEmail> frauderByEmail = fraudEmailRepository.findFrauderEmailByEmail(email);
        return frauderByEmail.map(FrauderEmail::isFraud).orElse(false);
    }
}
