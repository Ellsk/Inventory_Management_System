package com.example.invenotory_management_system;

public class ProductSearchModule {
    Integer productID;
    String brand, modelNumber;
    Integer modelYear;
    String productName, description;

   public ProductSearchModule(Integer productID, String brand, Integer modelYear, String modelNumber, String productName, String description){
        this.productID = productID;
        this.brand = brand;
        this.modelNumber = modelNumber;
        this.productName = productName;
        this.modelYear = modelYear;
        this.description = description;
   }

    public Integer getProductID() {
        return productID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
