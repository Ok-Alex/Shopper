package me.akulakovsky.shopper.entities.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import me.akulakovsky.shopper.entities.Product;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class SingleProductResponse {

    private Product product;

    @JsonProperty("retailer_logo")
    private String retailerLogo;

    @JsonProperty("recommendation_logic")
    private String[] recommendationLogic;


    public SingleProductResponse() {
    }

    public Product getProduct() {
        return product;
    }

    public String getRetailerLogo() {
        return retailerLogo;
    }

    public String[] getRecommendationLogic() {
        return recommendationLogic;
    }
}
