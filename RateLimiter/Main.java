import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
       
        List<RateLimitRule> activeRules = Arrays.asList(
            new RateLimitRule("5_req_per_min", 5, 60),
            new RateLimitRule("100_req_per_hr", 100, 3600)
        );

      
        executeSimulation("--- Testing: Fixed Window Counter ---", new FixedWindowAlgorithm(), activeRules);
        System.out.println("\n");
        
        
        executeSimulation("--- Testing: Sliding Window Counter ---", new SlidingWindowAlgorithm(), activeRules);
    }

    private static void executeSimulation(String header, RateLimiterAlgorithm algo, List<RateLimitRule> rules) {
        System.out.println(header);

        RateLimiter limiter = new RateLimiter(algo, rules);
        InternalService apiService = new InternalService(limiter);

        
        boolean[] incomingRequests = {false, true, true, true, true, true, true};

        int clockTime = 0;
        for (int i = 0; i < incomingRequests.length; i++) {
            String payloadData = "DataPayload-" + (i + 1);
            apiService.handleRequest("Tenant_Alpha", incomingRequests[i], payloadData, clockTime);
            
           
            clockTime += 9; 
        }
    }
}