package practice_system_design.machine_coding.tax_billing.service;

import practice_system_design.machine_coding.tax_billing.entity.FinalProduct;
import practice_system_design.machine_coding.tax_billing.entity.Product;
import practice_system_design.machine_coding.tax_billing.entity.Tax;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaxBillingService {

    public List<FinalProduct> getFinalPrices(List<Tax> taxes, List<Product> products) {

        List<Tax> fixedTaxes = getFixedTax(taxes);
        List<Tax> percentageTaxes = getPercentageTax(taxes);
        List<FinalProduct> finalProducts = new ArrayList<>();

        for (Product product : products) {
            FinalProduct finalProduct = getFinalProduct(product, fixedTaxes, percentageTaxes);
            finalProducts.add(finalProduct);
        }
        return finalProducts;
    }

    private FinalProduct getFinalProduct(Product product, List<Tax> fixedTaxes, List<Tax> percentageTaxes) {
        List<Tax> fixedTaxApply = getFixedTaxApply(product, fixedTaxes);
        List<Tax> percentageTaxApply = getPercentageTaxApply(product, percentageTaxes);
        int fixedTaxSum = fixedTaxApply.stream().mapToInt(e -> e.getTax()).sum();

        double percentageTaxSum = percentageTaxApply.stream().mapToDouble(e -> calculatePercentageTax(product, e.getTax())).sum();
        double discountPrice = 0;
        if (product.getDiscount() != null) {
            discountPrice = (product.getBasePrice() * product.getDiscount()) / 100;
        }

        double finalPrice = product.getBasePrice() + fixedTaxSum + percentageTaxSum - discountPrice;
        return new FinalProduct(product.getItem(), product.getBasePrice(), finalPrice);

    }

    private double calculatePercentageTax(Product product, int tax) {
        double productPrice = product.getBasePrice();

        return (productPrice * tax) / 100.0;
    }


    private List<Tax> getPercentageTaxApply(Product product, List<Tax> percentageTaxes) {
        List<Tax> percentageTaxApply = new ArrayList<>();
        for (Tax tax : percentageTaxes) {
            if (tax.getMinPrice() <= product.getBasePrice()) {
                if (tax.getMaxPrice() != null) {
                    if (tax.getMaxPrice() >= product.getBasePrice()) {
                        percentageTaxApply.add(tax);
                    }
                } else {
                    percentageTaxApply.add(tax);
                }
            }
        }
        return percentageTaxApply;
    }

    private List<Tax> getFixedTaxApply(Product product, List<Tax> fixedTaxes) {
        return fixedTaxes.stream()
                .filter(e -> product.getBasePrice() - e.getMinPrice() >= 0)
                .collect(Collectors.toList());
    }


    private List<Tax> getPercentageTax(List<Tax> taxes) {
        return taxes.stream()
                .filter(e -> e.getTaxType().name().equals("PERCENTAGE"))
                .collect(Collectors.toList());
    }

    private List<Tax> getFixedTax(List<Tax> taxes) {
        return taxes.stream()
                .filter(e -> e.getTaxType().name().equals("FIXED"))
                .collect(Collectors.toList());
    }
}
