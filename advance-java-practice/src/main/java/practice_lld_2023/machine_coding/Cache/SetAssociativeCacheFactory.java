package practice_lld_2023.machine_coding.Cache;

import practice_lld_2023.machine_coding.Cache.algorithm.LRUReplacementAlgorithm;
import practice_lld_2023.machine_coding.Cache.algorithm.MRUReplacementAlgorithm;

public class SetAssociativeCacheFactory {
    /// NOTE: replacementAlgoName is provided in case you need it here.
    // Whether you do will depend on your interface design.
    public static SetAssociativeCache<String, String> CreateNWayCache(
            int setCount, int setSize, String replacementAlgoName) {

        switch (replacementAlgoName) {
            case "LRUReplacementAlgorithm":
                return new SetAssociativeCache<>(setCount, setSize, new LRUReplacementAlgorithm());
            case "MRUReplacementAlgorithm":
                return new SetAssociativeCache<>(setCount, setSize, new MRUReplacementAlgorithm());
            default:
                return null;
        }
    }
}
