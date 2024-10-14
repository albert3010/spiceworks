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
    public int maxGoodNumber(int[] nums) {

        String a = Integer.toBinaryString(nums[0]);
        String b = Integer.toBinaryString(nums[1]);
        String c = Integer.toBinaryString(nums[2]);
        int max=0;

        max  = Math.max(max, getInt(a, b, c));
        max  = Math.max(max, getInt(a, c, b));

        max  = Math.max(max, getInt(b, a, c));
        max  = Math.max(max, getInt( b, c, a));

        max  = Math.max(max, getInt(c, a, b));
        return Math.max(max, getInt(c, b, a));
    }

    int getInt(String a, String b, String c){
        String r = a+b+c;
        return Integer.parseInt(r);
    }
}
