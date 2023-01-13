package practice_lld_2023.machine_coding.MessageQueue;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {

    private String topicName;
    private List<String> data;

    public Topic(String topicName) {
        this.topicName = topicName;
        this.data = new ArrayList<>();
    }

    public void addEvent(String message){
        this.data.add(message);
    }

}
