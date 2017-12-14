/*
package com.yeta.hbase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

*/
/**
 * Created by Administrator on 2017-12-11.
 *//*

@Entity
@Table(name = "car_config")
public class CarConfig {

    */
/**
     * id
     *//*

    @Id
    @Column(name = "id_")
    private String id;

    */
/**
     * **********************************************************基本信息
     * 品牌
     *//*

    @Column(name = "brand_")
    private String brand;

    */
/**
     * 车系
     *//*

    @Column(name = "car_series")
    private String carSeries;

    */
/**
     * 车型名称
     *//*

    @Column(name = "car_type")
    private String carType;

    */
/**
     * 年款
     *//*

    @Column(name = "year_")
    private String year;

    */
/**
     * 厂商指导价
     *//*

    @Column(name = "price_")
    private String price;

    */
/**
     * 厂商
     * **********************************************************
     *//*

    @Column(name = "manufacturer_")
    private String manufacturer;

    */
/**
     * **********************************************************基本属性
     * 级别
     *//*

    @Column(name = "grade_")
    private String grade;

    */
/**
     * 长*宽*高
     *//*

    @Column(name = "size_")
    private String size;

    */
/**
     * 车身结构
     *//*

    @Column(name = "car_structure")
    private String carStructure;

    */
/**
     * 车门数
     *//*

    @Column(name = "car_doors")
    private String carDoors;

    */
/**
     * 座位数
     *//*

    @Column(name = "car_seats")
    private String carSeats;

    */
/**
     * 变速箱（手动、自动）
     * **********************************************************
     *//*

    @Column(name = "gearbox_")
    private String gearbox;

    */
/**
     * **********************************************************性能属性
     * 排量
     *//*

    @Column(name = "displacemen_")
    private String displacemen;

    */
/**
     * 最高车速
     *//*

    @Column(name = "max_speed")
    private String maxSpeed;

    */
/**
     * 0-100KM加速（S）
     *//*

    @Column(name = "0_100_acceleration_capability")
    private String accelerationCapability;

    */
/**
     * 发动机
     *//*

    @Column(name = "engine_")
    private String engine;

    */
/**
     * 油箱容积
     *//*

    @Column(name = "oil_tank_capacity")
    private String oilTankCapacity;

    */
/**
     * 气缸数
     *//*

    @Column(name = "car_cylinders")
    private String carCylinders;

    */
/**
     * 供油方式
     * **********************************************************
     *//*

    @Column(name = "oil_drive")
    private String oilDrive;

    public CarConfig() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "CarConfig{" +
                "id=" + id +
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
*/
