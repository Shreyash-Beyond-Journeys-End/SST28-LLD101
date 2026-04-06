import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowAlgorithm implements RateLimiterAlgorithm {

    private final Map<String, WindowData> stateMap = new ConcurrentHashMap<>();
    private final Map<String, Object> mutexes = new ConcurrentHashMap<>();

    @Override
    public boolean isAllowed(String key, List<RateLimitRule> rules, int currentTime) {
        Object lock = mutexes.computeIfAbsent(key, k -> new Object());

        synchronized (lock) {
            
            for (RateLimitRule rule : rules) {
                WindowData tracker = fetchOrCreateData(key, rule);
                int activeWindow = (currentTime / rule.getWindowSize()) * rule.getWindowSize();

                
                if (tracker.windowStart < activeWindow) {
                    tracker.windowStart = activeWindow;
                    tracker.count = 0;
                }

                if (tracker.count >= rule.getMaxRequests()) {
                    return false; 
                }
            }

            
            for (RateLimitRule rule : rules) {
                WindowData tracker = fetchOrCreateData(key, rule);
                tracker.count++;
            }

            return true;
        }
    }

    private WindowData fetchOrCreateData(String key, RateLimitRule rule) {
        String compositeKey = key + "||" + rule.getName();
        return stateMap.computeIfAbsent(compositeKey, k -> new WindowData());
    }

    private static class WindowData {
        int windowStart = -1;
        int count = 0;
    }
}