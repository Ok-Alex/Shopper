package me.akulakovsky.shopper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class ProductColor {

    private String name;

    @JsonProperty("swatch_url")
    private String swatchUrl;

    @JsonProperty("available_sizes")
    private String[] availableSizes;

    public ProductColor() {
    }

    public String getName() {
        return name;
    }

    public String getSwatchUrl() {
        return swatchUrl;
    }

    public String[] getAvailableSizes() {
        return availableSizes;
    }
}
