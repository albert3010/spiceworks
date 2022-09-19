package practice_lld_2023.lld_2022.Cache.test;

import practice_lld_2023.lld_2022.Cache.SetAssociativeCache;
import practice_lld_2023.lld_2022.Cache.SetAssociativeCacheFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class PerformanceTests {
    @Test
    public void testPerformance() throws IOException {
        long startTime = System.currentTimeMillis();
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(1,10000,"LRUReplacementAlgorithm");
        for (Integer i = 0; i< 25000; i++){
            nWayCache.set(i.toString(), i.toString());
            nWayCache.get(i.toString());
        }
        long endTime = System.currentTimeMillis();

        Long totalTime = endTime - startTime;
        Assert.assertTrue( "Test exceeded maximum time limit of 14 seconds, Total time: " + totalTime, totalTime < 7000);
    }

    @Test
    public void testGetSetIndexPerformance() throws IOException {
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(10000,1,"LRUReplacementAlgorithm");
        long startTime = System.currentTimeMillis();
        for (Integer i = 0; i< 25000; i++){
            nWayCache.set(i.toString(), i.toString());
        }
        long endTime = System.currentTimeMillis();

        Long totalTime = endTime - startTime;
        Assert.assertTrue( "Test exceeded maximum time limit of 1 second, Total time: " + totalTime, totalTime < 1000);
    }

    @Test
    public void testAdding10xCapacity() throws IOException {
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(3,3,"MRUReplacementAlgorithm");
        for (Integer i=0; i<90; i++){
            nWayCache.set(i.toString(), i.toString());

            Assert.assertEquals(i.toString(), nWayCache.get(i.toString()));
        }

        Assert.assertEquals(9, nWayCache.getCount());
    }
}
