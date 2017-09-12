package me.akulakovsky.shopper.entities;

/**
 * Created by akulakovsky on 9/12/17.
 */

public class ProductSize {

    private String name;
    private CanonicalSize canonicalSize;

    public ProductSize() {
    }

    public String getName() {
        return name;
    }

    public CanonicalSize getCanonicalSize() {
        return canonicalSize;
    }
}
