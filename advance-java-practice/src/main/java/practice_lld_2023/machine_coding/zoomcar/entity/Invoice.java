package practice_lld_2023.machine_coding.zoomcar.entity;

import practice_lld_2023.machine_coding.zoomcar.constants.Constant;
import practice_lld_2023.machine_coding.zoomcar.constants.PaymentMode;
import practice_lld_2023.machine_coding.zoomcar.constants.PaymentStatus;

import java.util.Calendar;
import java.util.Date;

public class Invoice {
    int id;
    int inventoryId;
    PaymentStatus paymentStatus;
    Double totalAmount;
    PaymentMode paymentMode;
    Date createdAt;
    Date updatedAt;

    public Invoice(int inventoryId, PaymentStatus paymentStatus, Double totalAmount, PaymentMode paymentMode) {
        this.id = Constant.getInvoiceId();
        this.inventoryId = inventoryId;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.paymentMode = paymentMode;
        this.createdAt = Calendar.getInstance().getTime();
    }
}
