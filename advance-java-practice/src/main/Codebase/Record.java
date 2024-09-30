import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Record {
    private String key;
    private String value;
    private Long ttl;

    public Record(String key, String value) {
        this.key = key;
        this.value = value;
        this.ttl = 0l;
    }

    void updateTtl(long expiryTime){
        this.ttl = expiryTime;
    }

}
