package com.yeta.hbase.domain;

/**
 * Created by Administrator on 2017-12-12.
 */
public class PerformanceAttribute {

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

    public PerformanceAttribute() {
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
        return "PerformanceAttribute{" +
                "displacemen='" + displacemen + '\'' +
                ", maxSpeed='" + maxSpeed + '\'' +
                ", accelerationCapability='" + accelerationCapability + '\'' +
                ", engine='" + engine + '\'' +
                ", oilTankCapacity='" + oilTankCapacity + '\'' +
                ", carCylinders='" + carCylinders + '\'' +
                ", oilDrive='" + oilDrive + '\'' +
                '}';
    }
}
