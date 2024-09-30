import evictionPolicy.EvictionPolicy;
import evictionPolicy.LRUEvictionPolicy;

public class Tests {

    public static void main(String[] args) throws InterruptedException {

        EvictionPolicy evictionPolicy = new LRUEvictionPolicy(3);
        CacheProvider cacheProvider = new CacheProvider(evictionPolicy);

        cacheProvider.put("k1", "v1");
        cacheProvider.put("k2", "v2");
        cacheProvider.put("k3", "v3", 1000);
        cacheProvider.put("k4", "v4");
        Thread.sleep(1000);
        cacheProvider.put("k5", "v5");
        System.out.println(cacheProvider.get("k1"));
        System.out.println(cacheProvider.get("k2"));
        System.out.println("k4 " + cacheProvider.get("k4"));
        System.out.println("k5 " + cacheProvider.get("k5"));

    }
}
