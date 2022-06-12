import entity.Address;
import entity.Buyer;
import entity.PaymentMode;
import entity.Product;
import service.BuyerService;
import service.OrderService;
import service.PincodeService;
import service.ProductService;

public class EcommerceApp {
    public static void main(String[] args) throws Exception {

        Address address1 = new Address(101, "block 112");
        Address address2 = new Address(102, "block 102");

        Address productAddress1 = new Address(103, "block 103");
        Address productAddress2 = new Address(104, "block 104");

        Buyer buyer1 = new Buyer("Om", address1);
        Buyer buyer2 = new Buyer("Rahul", address2);


        BuyerService buyerService = new BuyerService();

        buyerService.addBuyer(buyer1);
        buyerService.addBuyer(buyer2);


        Product productChair = new Product("chair", 1000, productAddress1, 5);
        Product productMobile = new Product("mobile", 9999, productAddress2, 10);

        ProductService productService = new ProductService();

        productService.addProduct(productChair);
        productService.addProduct(productMobile);

        PincodeService pincodeService = new PincodeService();

        pincodeService.addServiceability(productAddress1.getPinCode(), address1.getPinCode(), PaymentMode.COD);
        pincodeService.addServiceability(productAddress2.getPinCode(), address2.getPinCode(), PaymentMode.PREPAID);

        OrderService orderService = new OrderService(buyerService, productService, pincodeService);

        int orderId = orderService.createOrder(buyer1.getBuyerId(), productChair.getProductId(), 4, PaymentMode.COD);
        System.out.println("order id is " + orderId);
        int orderId2 = orderService.createOrder(buyer1.getBuyerId(), productChair.getProductId(), 1, PaymentMode.COD);
        System.out.println("order id is " + orderId2);

    }


}
