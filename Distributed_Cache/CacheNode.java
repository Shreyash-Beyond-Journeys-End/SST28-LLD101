import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private final String nodeId;
    private final int capacity;
    private final Map<String, String> dataStore;
    private final EvictionPolicy evictionPolicy;

    public CacheNode(String nodeId, int capacity, EvictionPolicy evictionPolicy) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.evictionPolicy = evictionPolicy;
        this.dataStore = new HashMap<>();
    }

    public String get(String key) {
        String cachedValue = dataStore.get(key);
        if (cachedValue != null) {
            evictionPolicy.keyAccessed(key);
        }
        return cachedValue;
    }

    public void put(String key, String value) {
        boolean isNewKey = !dataStore.containsKey(key);
        
        if (isNewKey && dataStore.size() >= capacity) {
            String evictCandidate = evictionPolicy.getKeyToEvict();
            if (evictCandidate != null) {
                dataStore.remove(evictCandidate);
                evictionPolicy.removeKey(evictCandidate);
            }
        }

        dataStore.put(key, value);
        evictionPolicy.keyAccessed(key);
    }

    public String getNodeId() {
        return this.nodeId;
    }
}