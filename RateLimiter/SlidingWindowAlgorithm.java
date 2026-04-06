import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowAlgorithm implements RateLimiterAlgorithm {

    private final Map<String, SlidingData> trackerMap = new ConcurrentHashMap<>();
    private final Map<String, Object> keyLocks = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowed(String key, List<RateLimitRule> rules, int currentTime) {
        Object syncObj = keyLocks.computeIfAbsent(key, k -> new Object());

        synchronized (syncObj) {
            
            for (RateLimitRule rule : rules) {
                SlidingData data = getTracker(key, rule);
                int size = rule.getWindowSize();
                int activeWindowStart = (currentTime / size) * size;

                
                if (data.currentWindowStart != activeWindowStart) {
                    
                    if (activeWindowStart - size == data.currentWindowStart) {
                        data.previousCount = data.currentCount;
                    } else {
                      
                        data.previousCount = 0; 
                    }
                    data.currentWindowStart = activeWindowStart;
                    data.currentCount = 0;
                }

                
                int timeElapsedInCurrentWindow = currentTime - activeWindowStart;
                double overlapRatio = 1.0 - ((double) timeElapsedInCurrentWindow / size);
                double estimatedTotal = data.currentCount + (data.previousCount * overlapRatio);

                
                if (estimatedTotal >= rule.getMaxRequests()) {
                    return false; 
                }
            }

            
            for (RateLimitRule rule : rules) {
                SlidingData data = getTracker(key, rule);
                data.currentCount++;
            }

            return true;
        }
    }

    private SlidingData getTracker(String key, RateLimitRule rule) {
        String mapKey = String.format("%s_@_%s", key, rule.getName());
        return trackerMap.computeIfAbsent(mapKey, k -> new SlidingData());
    }

    private static class SlidingData {
        int currentWindowStart = -1;
        int previousCount = 0;
        int currentCount = 0;
    }
}