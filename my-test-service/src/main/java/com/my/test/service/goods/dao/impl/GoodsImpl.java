package com.my.test.service.goods.dao.impl;

import com.my.test.service.goods.dao.GoodsDao;
import com.my.test.service.goods.model.Goods;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vivi on 2017/12/7.
 */
@Repository("goodsDao")
public class GoodsImpl extends SqlSessionDaoSupport implements GoodsDao {
    public void updateMount(Goods goods) {
        this.getSqlSession().update("Goods.updateGoodsAmount",goods);
    }

    @Override
    public List<Goods> findAll() {
        return this.getSqlSession().selectList("Goods.findAll");
    }
}
