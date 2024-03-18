
import com.ob.tsb.balances.setup.BaseWebAuthClientMokedTest;
import com.ob.tsb.balances.util.ApplicationConstants;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;


public class AuthServiceImplTest extends BaseWebAuthClientMokedTest {

    @Test
    @DisplayName("Success - Auth Valid Token")
    public  void getValidToken(){
        String consentId="45672";
        String token=authService.generateJwtToken(consentId);
        assertNotNull(token);

        Mockito.when(authService.validateToken(token)).thenReturn(true);

        Claims claims=authService.getClaimsFromJWt(token);

        assertNotNull(claims);
    }

    @Test
    @DisplayName("Failure - Fallback method")
    void testFallBackMethod() {
        String consentId="45672";
        String token=authService.generateJwtToken(consentId);
        RequestNotPermitted requestNotPermitted1= mock(RequestNotPermitted.class);
        ResponseEntity<Object> requestNotPermitted=authService.authServiceCbFallback(token, requestNotPermitted1);
        Mono<ResponseEntity<Object>> fallback=Mono.just(requestNotPermitted);
        StepVerifier.create(fallback)
                .expectNextMatches(stringResponseEntity ->
                        stringResponseEntity.getStatusCode().equals(HttpStatus.SERVICE_UNAVAILABLE)
                                && Objects.equals(stringResponseEntity.getBody(), ApplicationConstants.CIRCUIT_BREAKER_FALLBACK_MSG))
                .expectComplete()
                .verify();


    }

}
