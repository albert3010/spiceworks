import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queue {

    private static Queue queueInstance;
    private List<String> messages;
    private Map<String, Observer> consumerMap;
    private OffsetI offset;

    private Queue(){
        this.consumerMap = new HashMap<>();
        this.messages = new ArrayList<>();
        this.offset = new InMemoryOffset();
    }

    public static Queue getQueueInstance() {
        if(queueInstance == null){
            queueInstance = new Queue();
        }
        return queueInstance;
    }

    public void subscriber(Observer consumer){
        consumerMap.put(consumer.getConsumerId(), consumer);
    }

    public void pushMessage(String message){
        messages.add(message);
        processEventsForConsumer(message);
    }

    private void  processEventsForConsumer(String message){
        for (Observer consumer : consumerMap.values()){
            consumer.processEvents(message);
        }
    }
    public void unsubscribe(Consumer consumer){
        if(consumerMap.containsKey(consumer.getConsumerId())){
            consumerMap.remove(consumer);
        }else {
            throw new RuntimeException("consumer not found");
        }
    }

}
