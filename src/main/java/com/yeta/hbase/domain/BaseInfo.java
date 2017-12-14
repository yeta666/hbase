package com.yeta.hbase.domain;

/**
 * Created by Administrator on 2017-12-12.
 */
public class BaseInfo {

    /**
     * **********************************************************基本信息
     * 品牌
     */
    private String brand;

    /**
     * 车系
     */
    private String carSeries;

    /**
     * 车型名称
     */
    private String carType;

    /**
     * 年款
     */
    private String year;

    /**
     * 厂商指导价
     */
    private String price;

    /**
     * 厂商
     * **********************************************************
     */
    private String manufacturer;

    public BaseInfo() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "BaseInfo{" +
                "brand='" + brand + '\'' +
                ", carSeries='" + carSeries + '\'' +
                ", carType='" + carType + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
