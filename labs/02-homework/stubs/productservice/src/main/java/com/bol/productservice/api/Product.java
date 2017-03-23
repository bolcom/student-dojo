package com.bol.productservice.api;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Product {
    private final String ean;
    private String name;
    private BigDecimal suggestedRetailPrice, consumerPrice;
    private boolean available;

    public Product(String ean, String name, BigDecimal suggestedRetailPrice) {
        this.ean = ean;
        this.name = name;
        this.suggestedRetailPrice = round(suggestedRetailPrice);
        this.consumerPrice = round(suggestedRetailPrice);
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSuggestedRetailPrice() {
        return suggestedRetailPrice;
    }

    public void setSuggestedRetailPrice(BigDecimal suggestedRetailPrice) {
        this.suggestedRetailPrice = round(suggestedRetailPrice);
    }

    public BigDecimal getConsumerPrice() {
        return consumerPrice;
    }

    public void setConsumerPrice(BigDecimal consumerPrice) {
        this.consumerPrice = round(consumerPrice);
    }

    public String getEan() {
        return ean;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    private BigDecimal round(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
