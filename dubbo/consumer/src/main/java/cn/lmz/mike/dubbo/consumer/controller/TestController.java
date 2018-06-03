package cn.lmz.mike.dubbo.consumer.controller;

import cn.lmz.mike.dubbo.api.bean.TestBean;
import cn.lmz.mike.dubbo.api.service.ITestService;
import cn.lmz.mike.dubbo.common.Util;
import cn.lmz.mike.dubbo.consumer.service.RedisService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    private static Logger logger = LoggerFactory.getLogger("TestController");

    @Autowired
    private ITestService testService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/test")
    @ResponseBody
    public TestBean test(){

        String msg = "consumer TestController:"+ Util.getMsg();
        logger.info(msg);
        System.out.println(msg);

        Object id = redisService.get("RID");
        if(id==null) id = "0";


        TestBean b = new TestBean();
        b.setId(Integer.parseInt(id.toString()));
        b.setName("test");

        b = testService.test(b);

        redisService.set("RID",b.getId());
        Map<String,Object> m = new HashMap<String,Object>();
        m.put(b.getId()+"",b.getName());
        redisService.setHash("RHASH",m);


        Map<String,Object> mv = (Map)redisService.getHash("HASH");

        if(mv!=null&&mv.size()>0){
            for(Map.Entry<String,Object> en:mv.entrySet()) {
                System.out.println(en.getKey()+":"+en.getValue());
            }
        }

        return b;
    }

}
