package practice_system_design.machine_coding.tax_billing;

import practice_system_design.machine_coding.tax_billing.entity.FinalProduct;
import practice_system_design.machine_coding.tax_billing.entity.Product;
import practice_system_design.machine_coding.tax_billing.entity.Tax;
import practice_system_design.machine_coding.tax_billing.service.TaxBillingService;

import java.util.Arrays;
import java.util.List;

public class TaxBillingApp {

    public static void main(String[] args) {

        Tax tax1 = new Tax(1000, 7500, 12, TaxType.PERCENTAGE);
        Tax tax2 = new Tax(7500, 12, TaxType.PERCENTAGE);
        Tax tax3 = new Tax(0, 200, TaxType.FIXED);

        List<Tax> taxList = Arrays.asList(tax1, tax2, tax3);

        Product product1 = new Product("itemA", 120000, 5);
        Product product2 = new Product("itemB", 2000);
        List<Product> productList = Arrays.asList(product1, product2);

        TaxBillingService taxBillingService = new TaxBillingService();

        List<FinalProduct> finalProducts = taxBillingService.getFinalPrices(taxList, productList);

        for (FinalProduct finalProduct: finalProducts){
            System.out.println(finalProduct);
        }

    }

}
