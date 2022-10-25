package practice_system_design.machine_coding.tax_billing.entity;

import practice_system_design.machine_coding.tax_billing.TaxType;

public class Tax {
    int minPrice;
    Integer maxPrice;
    int tax;
    TaxType taxType;

    public Tax(int minPrice, Integer maxPrice, int tax, TaxType taxType) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.tax = tax;
        this.taxType = taxType;
    }

    public Tax(int minPrice, int tax, TaxType taxType) {
        this.minPrice = minPrice;
        this.tax = tax;
        this.taxType = taxType;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public int getTax() {
        return tax;
    }

    public TaxType getTaxType() {
        return taxType;
    }
}
