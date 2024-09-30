import evictionPolicy.EvictionPolicy;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheProvider {
    private TreeSet<Record> records;
    private EvictionPolicy evictionPolicy;
    private Map<String, Record> recordMap;

    public CacheProvider(EvictionPolicy evictionPolicy) {
        this.records = new TreeSet<Record>((a, b) -> Long.compare(a.getTtl(), b.getTtl()));
        this.evictionPolicy = evictionPolicy;
        this.recordMap = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> removeKeysThread());
    }

    public String get(String key) {
        return get(key, 0l);
    }

    public String get(String key, long expiryTime) {
        long currentEpochTime = DateTime.now().getMillis();
        if (expiryTime != 0l) {
            expiryTime = currentEpochTime + expiryTime;
        }
        Record record = recordMap.get(key);
        if (record == null) {
            return null;
        }
        evictionPolicy.recordUsage(key);
        record.updateTtl(expiryTime);
        if(expiryTime == 0l){
            records.remove(record);
        }

        return record.getValue();
    }

    public void put(String key, String value, long expiryTime) {
        long currentEpochTime = DateTime.now().getMillis();
        if (expiryTime != 0l) {
            expiryTime = currentEpochTime + expiryTime;
        }

        Record record = recordMap.get(key);
        if (record == null) {
            String ekey = evictionPolicy.getEvictionKey();
            Record newRecord = new Record(key, value, expiryTime);
            if (ekey != null && evictionPolicy.isCapacityFull() && !ekey.equals(key)) {
                evictionPolicy.removeKey(ekey);
                recordMap.remove(ekey);
            }
            evictionPolicy.recordUsage(key);
            recordMap.put(key, newRecord);

            if(expiryTime!=0l){
                records.add(newRecord);
            }
        } else {
            record.updateTtl(expiryTime);
            records.remove(record);
            records.add(record);
            evictionPolicy.recordUsage(key);
        }
    }

    public void put(String key, String value) {
        put(key, value, 0l);
    }

    private void removeKeysThread() {
        while (true) {
            long currentEpochTime = DateTime.now().getMillis();
            while (!records.isEmpty() && records.first().getTtl() < currentEpochTime) {
                Record record = records.first();
                if (record.getTtl()==0l) continue;
                evictionPolicy.removeKey(record.getKey());
                recordMap.remove(record.getKey());
                records.pollFirst();
                currentEpochTime = DateTime.now().getMillis();
                System.out.println("key removed "+ record.getKey());
            }
        }
    }
}
