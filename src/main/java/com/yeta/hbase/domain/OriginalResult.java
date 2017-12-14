package com.yeta.hbase.domain;

/**
 * Created by Administrator on 2017-12-11.
 */
public class OriginalResult {

    /**
     * 行健
     */
    private String row;

    /**
     * 列族
     */
    private String family;

    /**
     * 属性
     */
    private String qualifier;

    /**
     * 值
     */
    private String value;

    /**
     * 时间戳
     */
    private long timestamp;

    public OriginalResult() {
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OriginalResult{" +
                "row='" + row + '\'' +
                ", family='" + family + '\'' +
                ", qualifier='" + qualifier + '\'' +
                ", value='" + value + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
