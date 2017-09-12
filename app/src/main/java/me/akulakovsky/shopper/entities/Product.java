package me.akulakovsky.shopper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class Product {

    private String id;
    private String name;
    private String description;
    private String retailer;
    private String price;

    @JsonProperty("sale_price")
    private String salePrice;

    private List<ProductSize> sizes;

    @JsonProperty("shipping_cost")
    private double shippingCost;

    @JsonProperty("return_policy")
    private String returnPolicy;

    @JsonProperty("primary_photos")
    private List<ProductPhoto> primaryPhotos;

    @JsonProperty("secondary_photos")
    private List<ProductPhoto> secondaryPhotos;

    @JsonProperty("product_colors")
    private List<ProductColor> productColors;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getPrice() {
        return price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public List<ProductSize> getSizes() {
        return sizes;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public List<ProductPhoto> getPrimaryPhotos() {
        return primaryPhotos;
    }

    public List<ProductPhoto> getSecondaryPhotos() {
        return secondaryPhotos;
    }

    public List<ProductColor> getProductColors() {
        return productColors;
    }
}
