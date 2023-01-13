package practice_lld_2023.lld_2022.MessageQueue;

import practice_lld_2023.lld_2022.MessageQueue.consumer.Consumer;
import practice_lld_2023.lld_2022.MessageQueue.producer.Producer;

public class App {

    public static void main(String[] args) {

        QueueService queueService = new QueueService();

        Producer producer1 = new Producer("p1", 1, queueService);
        Producer producer2 = new Producer("p2", 2, queueService);

        Consumer consumer1 = new Consumer( 1, queueService);
        Consumer consumer2 = new Consumer( 2, queueService);
        Consumer consumer3 = new Consumer( 3, queueService);

        Consumer consumer4 = new Consumer( 4, queueService);
        Consumer consumer5 = new Consumer( 5, queueService);

        Topic topic1 = new Topic("t1");
        Topic topic2 = new Topic("t2");

        queueService.addTopic(topic1);
        queueService.addTopic(topic2);

        queueService.subscribeTopic("t1", consumer1);


        queueService.subscribeTopic("t2", consumer2);

        queueService.subscribeTopic("t1", consumer3);
        queueService.subscribeTopic("t2", consumer3);

        producer1.publish("t1", "message1");
        producer1.publish("t1", "message4");

        producer2.publish("t2", "message2");

        producer1.publish("t2", "message3");


        queueService.subscribeTopic("t1", consumer4);


        queueService.subscribeTopic("t2", consumer5);


    }
}
