package com.my.test.service.goods.service.impl;

import com.my.test.service.goods.dao.GoodsDao;
import com.my.test.service.goods.model.Goods;
import com.my.test.service.goods.service.GoodsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;

/**
 * Created by vivi on 2017/12/7.
 */
@Transactional
@Service(value = "goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public void updateMount(Goods goods) {
        goodsDao.updateMount(goods);
    }

    class MyThread implements Runnable{
        private Goods goods;
        private GoodsDao goodsDao;
        private CountDownLatch countDownLatch;
        public MyThread(Goods goods,GoodsDao goodsDao,CountDownLatch countDownLatch){
            this.goods = goods;
            this.goodsDao = goodsDao;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
                goodsDao.updateMount(goods);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void test1(){
        int n = 3000;
        CountDownLatch countDownLatch = new CountDownLatch(n);
        Goods goods = new Goods();
        goods.setId(1);
        goods.setVersion(642);
        goods.setBuyAmount(1);

        for (int i=0;i<n;i++){//模拟3000个并发抢购
            try {
                MyThread myThread = new MyThread(goods,goodsDao,countDownLatch);
                new Thread(myThread).start();
            }finally {
                countDownLatch.countDown();
            }
        }
    }
}
