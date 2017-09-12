package me.akulakovsky.shopper.utils;

import me.akulakovsky.shopper.entities.responses.SimilarProductsResponse;
import me.akulakovsky.shopper.entities.responses.SingleProductResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET(Constants.SINGLE_PRODUCT_URL)
    Call<SingleProductResponse> getSingleProduct();

    @GET(Constants.SIMILAR_PRODUCTS_URL)
    Call<SimilarProductsResponse> getSimilarProducts();
}