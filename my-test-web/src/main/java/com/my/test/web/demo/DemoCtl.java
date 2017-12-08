package com.my.test.web.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/demoCtl")
public class DemoCtl {

    @RequestMapping("/index")
    public String index(){
        return "/index";
    }

    @ResponseBody
    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public List<String> getData(HttpServletRequest req, HttpServletResponse res) {
        List<String> list = new ArrayList<String>();
        list.add("java|001");
        list.add("c|002");
        list.add("c++|003");
        list.add("php|004");
        list.add("[python|005");
        list.add("android|006");
        list.add("ios|007");
        list.add("中华人民共和国|008");
        list.add("北京|009");
        list.add("I'm interested in sport|010");
        return list;
    }

}