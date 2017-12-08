package com.my.test.web.person;

import com.my.test.service.person.model.Person;
import com.my.test.service.person.service.impl.PersonServiceImpl;
import com.my.test.service.person.service.PersonService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vivi on 2017/11/25.
 */
@Controller
@RequestMapping(value = "/person")
public class PersonCtl {

    private static final Log logger = LogFactory.getLog(PersonServiceImpl.class);

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/index")
    public String index(){
        return "/person/index";
    }

    @ResponseBody
    @RequestMapping(value = "allPerson")
    public List<Person> findAllPerson(){
        List<Person> list = null;
        try {
            list = personService.findAllPerson();
        }catch (Exception e){
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
        return list;
    }

    @RequestMapping(value = "/updatePerson")
    public void updatePerson(){
        try {
            Person person = new Person();
            person.setId(3);
            person.setName("汤姆3");
            person.setAge(51);
            person.setAddress("美国波士顿");
            personService.updatePersonByid(person);
        }catch (Exception e){
            e.printStackTrace();
            logger.debug(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/whoYouLike")
    public Map whoYouLike(HttpServletRequest request){
        Map res = new HashMap();
        try {
            String pName = request.getParameter("pName");
            pName = java.net.URLDecoder.decode(pName,"UTF-8");
            if("胖狗子".equals(pName) || "伟".equals(pName)||"晶".equals(pName)|| "可爱".equals(pName)){
                res.put("res","01");
                return res;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        res.put("res","02");
        return res;
    }

    @RequestMapping(value = "/showFlower")
    public String showFlower(){
        return "/person/showFlower";
    }
}
