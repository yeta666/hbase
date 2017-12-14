package com.yeta.hbase.domain;

/**
 * Created by Administrator on 2017-12-12.
 */
public class BaseAttribute {

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

    public BaseAttribute() {
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

    @Override
    public String toString() {
        return "BaseAttribute{" +
                "grade='" + grade + '\'' +
                ", size='" + size + '\'' +
                ", carStructure='" + carStructure + '\'' +
                ", carDoors='" + carDoors + '\'' +
                ", carSeats='" + carSeats + '\'' +
                ", gearbox='" + gearbox + '\'' +
                '}';
    }
}
