import java.util.ArrayList;
import java.util.List;

public class DistributedCache {
    private final List<CacheNode> nodes;
    private final DistributionStrategy distributionStrategy;
    private final Database database;

    public DistributedCache(int numberOfNodes, int nodeCapacity, DistributionStrategy distributionStrategy, Database database) {
        this.distributionStrategy = distributionStrategy;
        this.database = database;
        this.nodes = new ArrayList<>(numberOfNodes);

        for (int idx = 0; idx < numberOfNodes; idx++) {
            CacheNode newNode = new CacheNode("node-" + idx, nodeCapacity, new LRUEvictionPolicy());
            this.nodes.add(newNode);
        }
    }

    public String get(String key) {
        CacheNode targetNode = getNode(key);
        String result = targetNode.get(key);

        if (result == null) {
           
            result = database.get(key);
            if (result != null) {
                targetNode.put(key, result);
            }
        }

        return result;
    }

    public void put(String key, String value) {
        CacheNode targetNode = getNode(key);
        targetNode.put(key, value);
        
       
        database.put(key, value);
    }

    public String getNodeIdForKey(String key) {
        CacheNode node = getNode(key);
        return node.getNodeId();
    }

    private CacheNode getNode(String key) {
        int selectedIndex = distributionStrategy.selectNode(key, nodes.size());
        return nodes.get(selectedIndex);
    }
}