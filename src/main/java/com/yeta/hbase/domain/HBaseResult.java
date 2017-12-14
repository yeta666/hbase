package com.yeta.hbase.domain;

/**
 * Created by Administrator on 2017-12-10.
 */
public class HBaseResult {

    /**
     * 操作是否成功
     */
    private boolean result = false;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 数据总数量
     */
    private Integer totalNumber;

    /**
     * 提示消息
     */
    private String message;

    public HBaseResult() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HBaseResult{" +
                "result=" + result +
                ", data=" + data +
                ", totalNumber=" + totalNumber +
                ", message='" + message + '\'' +
                '}';
    }
}
