package me.akulakovsky.shopper.entities.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import me.akulakovsky.shopper.entities.Product;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class SimilarProductsResponse {

    @JsonProperty("similar_products")
    private List<SingleProductResponse> similarProducts;

    public SimilarProductsResponse() {
    }

    public List<SingleProductResponse> getSimilarProducts() {
        return similarProducts;
    }
}
