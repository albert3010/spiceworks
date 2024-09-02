package practice_lld.top25.lld.stock.entity;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

@Getter
public class PendingOrders {

    private Map<Integer, PriorityQueue<Order>> buyQueue = new HashMap<>();
    private Map<Integer, PriorityQueue<Order>> sellQueue = new HashMap<>();

    public void addOrder(Order order){
        if (order.getOrderType() == OrderType.BUY){
            buyQueue.computeIfAbsent(order.getStockId(),
                            id -> new PriorityQueue<>((a, b) -> (int) (a.getTargetPrice() - b.getTargetPrice())))
                    .add(order);
        }else {
            sellQueue.computeIfAbsent(order.getStockId(),
                            id -> new PriorityQueue<>((a, b) -> (int) (a.getTargetPrice() - b.getTargetPrice())))
                    .add(order);
        }
    }
}
