package practice_lld.top25.lld.stock;

import practice_lld.top25.lld.stock.entity.Order;
import practice_lld.top25.lld.stock.entity.PendingOrders;
import practice_lld.top25.lld.stock.entity.Stock;

import java.util.Map;

public class StockExchange {
    Map<Integer, Stock> stockMap;
    PendingOrders pendingOrders;
    OrdersExecutor ordersExecutor;

    public StockExchange(Map<Integer, Stock> stockMap, OrdersExecutor ordersExecutor) {
        this.stockMap = stockMap;
        Thread ordersExecutorThread = new Thread(ordersExecutor);
        ordersExecutorThread.start();
    }

    void placeOrder(Order order) {
        pendingOrders.addOrder(order);
    }

}
