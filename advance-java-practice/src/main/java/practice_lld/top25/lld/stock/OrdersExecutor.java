package practice_lld.top25.lld.stock;

import org.joda.time.DateTime;
import practice_lld.top25.lld.stock.entity.*;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;

public class OrdersExecutor implements Runnable {
    PendingOrders pendingOrders;
    TransactionStore transactionStore;

    public OrdersExecutor(PendingOrders pendingOrders, TransactionStore transactionStore) {
        this.pendingOrders = pendingOrders;
        this.transactionStore = transactionStore;
    }

    @Override
    public void run() {
        while (true){
            Map<Integer, PriorityQueue<Order>> buyQueue =  pendingOrders.getBuyQueue();
            Map<Integer, PriorityQueue<Order>> sellQueue = pendingOrders.getSellQueue();

            for(int stockId  : buyQueue.keySet()){
                PriorityQueue<Order> buy = buyQueue.get(stockId);
                PriorityQueue<Order> sell = sellQueue.get(stockId);
                if(buy.peek().getTargetPrice() >= sell.peek().getTargetPrice()){
                    Order buyOrder = buy.poll();
                    Order sellOrder = sell.poll();

                    int tradeQuantity = Math.min(buyOrder.getQuantity(), sellOrder.getQuantity());

//                    Transaction sellTransaction = new Transaction(UUID.randomUUID().toString(), sellOrder.getTargetPrice(),
//                            sellOrder.getStockId(), sellOrder.getUserId(), OrderType.SELL, tradeQuantity, Status.SUCCESS, DateTime.now());
//                    Transaction buyTransaction = new Transaction(UUID.randomUUID().toString(), sellOrder.getTargetPrice(),
//                            sellOrder.stockId(), buyOrder.userId(), OrderType.BUY, tradeQuantity, Status.SUCCESS, DateTime.now());

//                    transactionStore.addTransaction(sellOrder.userId(), sellTransaction);
//                    transactionStore.addTransaction(buyTransaction.userId(), buyTransaction);
//
//                    if(buyOrder.quantity() > sellOrder.quantity()){
//                        // need to update q
//                        buy.add(buyOrder);
//                    }else {
//                        sell.add(sellOrder);
//                    }
                }
            }
        }

    }
}
