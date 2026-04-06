public class ModuloDistributionStrategy implements DistributionStrategy {
    @Override
    public int selectNode(String key, int numberOfNodes) {
        if (numberOfNodes < 1) {
            throw new IllegalArgumentException("Node count must be strictly positive.");
        }

        
        return Math.abs(key.hashCode()) % numberOfNodes;
    }
}