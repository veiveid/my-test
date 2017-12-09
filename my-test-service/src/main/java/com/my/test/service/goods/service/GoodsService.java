package com.my.test.service.goods.service;

import com.my.test.service.goods.model.Goods;

import java.util.List;

/**
 * Created by vivi on 2017/12/7.
 */
public interface GoodsService {
    void updateMount(Goods goods);

    void test1();

    List<Goods> findAll();
}
