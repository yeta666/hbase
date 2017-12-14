package com.yeta.hbase.domain;

import java.util.List;

/**
 * Created by Administrator on 2017-12-12.
 */
public class ColumnResult {

    /**
     * 行健
     */
    private String row;

    /**
     * 时间戳
     */
    private List<Long> timestamps;

    /**
     * 基本信息
     */
    private List<BaseInfo> baseInfos;

    /**
     * 基本属性
     */
    private List<BaseAttribute> baseAttributes;

    /**
     * 性能属性
     */
    private List<PerformanceAttribute> performanceAttributes;

    public ColumnResult() {
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public List<Long> getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(List<Long> timestamps) {
        this.timestamps = timestamps;
    }

    public List<BaseInfo> getBaseInfos() {
        return baseInfos;
    }

    public void setBaseInfos(List<BaseInfo> baseInfos) {
        this.baseInfos = baseInfos;
    }

    public List<BaseAttribute> getBaseAttributes() {
        return baseAttributes;
    }

    public void setBaseAttributes(List<BaseAttribute> baseAttributes) {
        this.baseAttributes = baseAttributes;
    }

    public List<PerformanceAttribute> getPerformanceAttributes() {
        return performanceAttributes;
    }

    public void setPerformanceAttributes(List<PerformanceAttribute> performanceAttributes) {
        this.performanceAttributes = performanceAttributes;
    }

    @Override
    public String toString() {
        return "ColumnResult{" +
                "row='" + row + '\'' +
                ", timestamps=" + timestamps +
                ", baseInfos=" + baseInfos +
                ", baseAttributes=" + baseAttributes +
                ", performanceAttributes=" + performanceAttributes +
                '}';
    }
}
