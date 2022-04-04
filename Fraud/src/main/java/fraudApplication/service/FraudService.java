package fraudApplication.service;

import fraudApplication.model.Frauder;
import fraudApplication.repository.FraudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class FraudService {
    private final FraudRepository fraudRepository;

    public boolean isFraud (Integer id) {
        Frauder frauder = fraudRepository.getById(id);
        return frauder.isIsfraud();
    }
    public boolean isCustomerFraud (Integer customerId) {
        Frauder frauder = fraudRepository.findFrauderBycustomerId(customerId).orElse(null);
        if (frauder!=null)
        return frauder.isIsfraud();
        else return true;
    }
}
