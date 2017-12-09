package com.my.test.service.goods.dao;

import com.my.test.service.goods.model.Goods;

import java.util.List;

/**
 * Created by vivi on 2017/12/7.
 */
public interface GoodsDao {
    void updateMount(Goods goods);

    List<Goods> findAll();
}
