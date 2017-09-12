package me.akulakovsky.shopper.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class ProductPhoto {

    @JsonProperty("web_url")
    private String webUrl;

    public ProductPhoto() {
    }

    public String getWebUrl() {
        return webUrl;
    }
}
