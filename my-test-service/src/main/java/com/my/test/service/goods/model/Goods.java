package com.my.test.service.goods.model;

/**
 * Created by vivi on 2017/12/7.
 */
public class Goods {
    private Integer id;
    private String name;
    private Integer amount;
    private Integer version;
    private Integer buyAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }
}
