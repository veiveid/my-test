package com.my.test.goods;

import com.my.test.service.goods.dao.GoodsDao;
import com.my.test.service.goods.model.Goods;
import com.my.test.service.goods.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

/**
 * Created by vivi on 2017/12/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-base.xml"})
public class GoodsTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void test1() {
        try {
            goodsService.test1();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

