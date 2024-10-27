import java.util.HashMap;
import java.util.Map;

public class InMemoryOffset implements OffsetI{

    Map<String, Integer> consumerOffset;

    public InMemoryOffset() {
        this.consumerOffset = new HashMap<>();
    }

    @Override
    public int getOffset(String consumerId) {
        return consumerOffset.getOrDefault(consumerId, 0);
    }

    @Override
    public void updateOffset(String consumerId, int offset) throws Exception {
        if(offset<0) throw new Exception("offset can't be negetive");
        consumerOffset.put(consumerId, offset);
    }
}
