package service;

import entity.PaymentMode;

import java.util.HashMap;
import java.util.Map;

public class PincodeService {

    Map<String, PaymentMode> pincodeToPaymentMode = new HashMap<>();

    public void addServiceability(int sourceCode, int destinationCode, PaymentMode paymentMode) {
        String sourceDestinationCodeKey = sourceCode + "," + destinationCode;
        pincodeToPaymentMode.put(sourceDestinationCodeKey, paymentMode);
    }

    public boolean isServiceability(int sourceCode, int destinationCode, PaymentMode paymentMode) {
        String sourceDestinationCodeKey = sourceCode + "," + destinationCode;
        PaymentMode payment = pincodeToPaymentMode.get(sourceDestinationCodeKey);
        if (payment != null && payment.name().equals(paymentMode.name())) {
            return true;
        }
        return false;
    }


}
