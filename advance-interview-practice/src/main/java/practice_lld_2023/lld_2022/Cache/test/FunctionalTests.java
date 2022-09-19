package practice_lld_2023.lld_2022.Cache.test;

import java.io.IOException;

import practice_lld_2023.lld_2022.Cache.SetAssociativeCache;
import practice_lld_2023.lld_2022.Cache.SetAssociativeCacheFactory;
import org.junit.Assert;
import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;

public class FunctionalTests {

    @Test
    public void testLruBasicFunctionality() throws IOException {
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(1,2,"LRUReplacementAlgorithm");
        nWayCache.set("Eggs", "Ham");
        nWayCache.set("Sam", "Iam");
        nWayCache.set("Green", "EggsAndHam");
        Assert.assertEquals("EggsAndHam", nWayCache.get("Green"));
        Assert.assertEquals("Iam", nWayCache.get("Sam"));
        Assert.assertFalse(nWayCache.containsKey("Eggs"));
        Assert.assertTrue(nWayCache.containsKey("Sam"));
        Assert.assertTrue(nWayCache.containsKey("Green"));
        nWayCache.set("Seuss", "Story");
        Assert.assertFalse(nWayCache.containsKey("Green"));
        Assert.assertTrue(nWayCache.containsKey("Sam"));
        Assert.assertTrue(nWayCache.containsKey("Seuss"));
    }

    @Test
    public void testMruBasicFunctionality() throws IOException {
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(1,2,"MRUReplacementAlgorithm");
        nWayCache.set("Eggs", "Ham");
        nWayCache.set("Sam", "Iam");
        nWayCache.set("Green", "EggsAndHam");
        Assert.assertEquals("EggsAndHam", nWayCache.get("Green"));
        Assert.assertEquals("Ham", nWayCache.get("Eggs"));
        Assert.assertTrue(nWayCache.containsKey("Eggs"));
        Assert.assertFalse(nWayCache.containsKey("Sam"));
        Assert.assertTrue(nWayCache.containsKey("Green"));
        nWayCache.set("Seuss", "Story");
        Assert.assertTrue(nWayCache.containsKey("Green"));
        Assert.assertFalse(nWayCache.containsKey("Eggs"));
        Assert.assertTrue(nWayCache.containsKey("Seuss"));
    }

    @Test
    public void testSimpleCacheInserts() throws IOException {
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(3,4,"LRUReplacementAlgorithm");
        nWayCache.set("test1111", "didthiswork?");
        nWayCache.set("Sam", "Iam");
        nWayCache.set("Green", "EggsAndHam");
        nWayCache.set("alacazathrus", "shazzam");
        nWayCache.set("alacazathrus", "smiggitybushnu");

        Assert.assertEquals("smiggitybushnu", nWayCache.get("alacazathrus"));
        Assert.assertEquals("didthiswork?", nWayCache.get("test1111"));
        Assert.assertTrue(nWayCache.containsKey("Sam"));
        Assert.assertTrue(nWayCache.containsKey("Green"));
    }

    @Test
    public void testSimpleCacheEvictions() throws IOException {
        SetAssociativeCache<String,String> nWayCache = SetAssociativeCacheFactory.CreateNWayCache(1,4,"LRUReplacementAlgorithm");
        nWayCache.set("test1111", "didthiswork?");
        nWayCache.set("Sam", "Iam");
        nWayCache.set("Green", "EggsAndHam");
        nWayCache.set("alacazathrus", "shazzam");
        nWayCache.set("alacazathrus", "smiggitybushnu");

        Assert.assertEquals("smiggitybushnu", nWayCache.get("alacazathrus"));
        Assert.assertEquals("didthiswork?", nWayCache.get("test1111"));
        Assert.assertTrue(nWayCache.containsKey("Sam"));
        Assert.assertTrue(nWayCache.containsKey("Green"));
    }
}
