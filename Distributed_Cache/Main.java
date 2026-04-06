public class Main {
    public static void main(String[] args) {
        Database dbService = new InMemoryDatabase();
        dbService.put("A", "Apple");
        dbService.put("B", "Ball");

        DistributedCache cacheCluster = new DistributedCache(
                3,
                2,
                new ModuloDistributionStrategy(),
                dbService
        );

        cacheCluster.put("C", "Cat");

        System.out.println("Value for A -> " + cacheCluster.get("A"));
        System.out.println("Value for B -> " + cacheCluster.get("B"));
        System.out.println("Value for C -> " + cacheCluster.get("C"));
        System.out.println("Mapped node for key A -> " + cacheCluster.getNodeIdForKey("A"));
    }
}