package com.zjp.echartsdemo.entity;

public class Product {

    public String productName;
    public Integer nums;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    @Override
    public String toString() {
        return "Product [productName=" + productName + ", nums=" + nums + "]";
    }
}
