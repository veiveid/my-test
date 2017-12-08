package com.my.test.web.goods;

import com.my.test.service.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
