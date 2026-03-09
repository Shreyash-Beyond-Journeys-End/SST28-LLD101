public interface DataStore {
    void save(String name, String content);
    int countLines(String name);
} 
