package microservices.book.gamification.client;

import microservices.book.gamification.client.dto.MultiplicationResultAttempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * This implementation of MultiplicationResultAttemptClient interface connects to
 * the Multiplication microservice via REST.
 */
@Component
public class MultiplicationResultAttemptClientImpl implements  MultiplicationResultAttemptClient {

    private final RestTemplate restTemplate;

    private final String gatewayhost;

    @Autowired
    public MultiplicationResultAttemptClientImpl(final RestTemplate restTemplate,
                                                 @Value("${gatewayhost}") final String gatewayhost) {
        this.restTemplate = restTemplate;
        this.gatewayhost = gatewayhost;
    }

    @Override
    public MultiplicationResultAttempt retrieveMultiplicationResultAttemptbyId(final Long multiplicationResultAttemptId) {
        return restTemplate.getForObject(
                gatewayhost + "/results/" + multiplicationResultAttemptId,
                MultiplicationResultAttempt.class);
    }
}
