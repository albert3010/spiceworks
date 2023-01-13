package practice_lld_2023.lld_2022.MessageQueue.producer;

import practice_lld_2023.lld_2022.MessageQueue.QueueService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Producer {
    private String name;
    private Integer id;
    private QueueService queueService;

    public void publish(String topic, String message){
        queueService.publishMessage(topic, message);
    }

}
