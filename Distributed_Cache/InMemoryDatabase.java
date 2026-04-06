import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDatabase implements Database {
    private final Map<String, String> records = new ConcurrentHashMap<>();

    @Override
    public String get(String key) {
        return records.get(key);
    }

    @Override
    public void put(String key, String value) {
        records.put(key, value);
    }
}