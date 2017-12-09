package com.my.test.web.goods;

import com.my.test.service.goods.model.Goods;
import com.my.test.service.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by vivi on 2017/12/9.
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsCtl {

    @Autowired
    private GoodsService goodsService;

    @ResponseBody
    @RequestMapping(value = "/updateGoods")
    public String updateGoods(){
        goodsService.test1();
        return "{\"res\":\"ok\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/findAll.json")
    public String findAll(){
       // List<Goods> list = goodsService.findAll();
        return "{\"11\":\"22\"}";
    }
}
