package com.yeta.hbase.domain;

import java.util.Arrays;

/**
 * Created by Administrator on 2017-12-13.
 */
public class HbaseRequest {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 每页显示数据条数
     */
    private Integer pageSize;

    /**
     * 用于分页的 row key
     */
    private byte[] lastRow;

    /**
     * 用于修改的 row key
     */
    private String updateRow;

    /**
     * 用于查询的 row key
     */
    private String queryRow;

    /**
     * 用于删除的 row key
     */
    private String[] deleteRow;

    /**
     * 最低价格
     */
    private double minPrice;

    /**
     * 最高价格
     */
    private double maxPrice;

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

    /**
     * **********************************************************基本属性
     * 级别
     */
    private String grade;

    /**
     * 长*宽*高
     */
    private String size;

    /**
     * 车身结构
     */
    private String carStructure;

    /**
     * 车门数
     */
    private String carDoors;

    /**
     * 座位数
     */
    private String carSeats;

    /**
     * 变速箱（手动、自动）
     * **********************************************************
     */
    private String gearbox;

    /**
     * **********************************************************性能属性
     * 排量
     */
    private String displacemen;

    /**
     * 最高车速
     */
    private String maxSpeed;

    /**
     * 0-100KM加速（S）
     */
    private String accelerationCapability;

    /**
     * 发动机
     */
    private String engine;

    /**
     * 油箱容积
     */
    private String oilTankCapacity;

    /**
     * 气缸数
     */
    private String carCylinders;

    /**
     * 供油方式
     * **********************************************************
     */
    private String oilDrive;

    public HbaseRequest() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public byte[] getLastRow() {
        return lastRow;
    }

    public void setLastRow(byte[] lastRow) {
        this.lastRow = lastRow;
    }

    public String getUpdateRow() {
        return updateRow;
    }

    public void setUpdateRow(String updateRow) {
        this.updateRow = updateRow;
    }

    public String getQueryRow() {
        return queryRow;
    }

    public void setQueryRow(String queryRow) {
        this.queryRow = queryRow;
    }

    public String[] getDeleteRow() {
        return deleteRow;
    }

    public void setDeleteRow(String[] deleteRow) {
        this.deleteRow = deleteRow;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCarStructure() {
        return carStructure;
    }

    public void setCarStructure(String carStructure) {
        this.carStructure = carStructure;
    }

    public String getCarDoors() {
        return carDoors;
    }

    public void setCarDoors(String carDoors) {
        this.carDoors = carDoors;
    }

    public String getCarSeats() {
        return carSeats;
    }

    public void setCarSeats(String carSeats) {
        this.carSeats = carSeats;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getDisplacemen() {
        return displacemen;
    }

    public void setDisplacemen(String displacemen) {
        this.displacemen = displacemen;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getAccelerationCapability() {
        return accelerationCapability;
    }

    public void setAccelerationCapability(String accelerationCapability) {
        this.accelerationCapability = accelerationCapability;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getOilTankCapacity() {
        return oilTankCapacity;
    }

    public void setOilTankCapacity(String oilTankCapacity) {
        this.oilTankCapacity = oilTankCapacity;
    }

    public String getCarCylinders() {
        return carCylinders;
    }

    public void setCarCylinders(String carCylinders) {
        this.carCylinders = carCylinders;
    }

    public String getOilDrive() {
        return oilDrive;
    }

    public void setOilDrive(String oilDrive) {
        this.oilDrive = oilDrive;
    }

    @Override
    public String toString() {
        return "HbaseRequest{" +
                "tableName='" + tableName + '\'' +
                ", pageSize=" + pageSize +
                ", lastRow=" + Arrays.toString(lastRow) +
                ", updateRow='" + updateRow + '\'' +
                ", queryRow='" + queryRow + '\'' +
                ", deleteRow=" + Arrays.toString(deleteRow) +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", brand='" + brand + '\'' +
                ", carSeries='" + carSeries + '\'' +
                ", carType='" + carType + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", grade='" + grade + '\'' +
                ", size='" + size + '\'' +
                ", carStructure='" + carStructure + '\'' +
                ", carDoors='" + carDoors + '\'' +
                ", carSeats='" + carSeats + '\'' +
                ", gearbox='" + gearbox + '\'' +
                ", displacemen='" + displacemen + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", accelerationCapability='" + accelerationCapability + '\'' +
                ", engine='" + engine + '\'' +
                ", oilTankCapacity='" + oilTankCapacity + '\'' +
                ", carCylinders='" + carCylinders + '\'' +
                ", oilDrive='" + oilDrive + '\'' +
                '}';
    }
}
