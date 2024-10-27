public class Producer {
    private Queue queue;
    public Producer(Queue queue){
        this.queue = queue;
    }

    void produceMessage(String message){
        queue.pushMessage(message);
    }

}
