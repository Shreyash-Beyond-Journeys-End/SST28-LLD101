public class InternalService {
    private final RateLimiter rateLimiter;

    public InternalService(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public void handleRequest(String tenantId, boolean externalCallNeeded, String payload, int currentTime) {
        System.out.println("-> Incoming request detected for tenant: [" + tenantId + "]");

        if (externalCallNeeded) {
            String limiterKey = "TID_" + tenantId;
            
            if (rateLimiter.isAllowed(limiterKey, currentTime)) {
                System.out.println("   [SUCCESS] Rate limit check passed. Proceeding...");
                callExternalResource(payload);
            } else {
                System.out.println("   [BLOCKED] Too many requests. Rate limit exceeded!");
            }
        } else {
            System.out.println("   [SKIPPED] No external API call required. Bypassing rate limiter.");
        }
    }

    private void callExternalResource(String payload) {
        System.out.println("   >>> Invoking external resource with payload: {" + payload + "}");
    }
}