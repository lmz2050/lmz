package cn.lmz.mike.dubbo.provider.controller;

import cn.lmz.mike.dubbo.api.bean.TestBean;
import cn.lmz.mike.dubbo.api.service.ITestService;
import cn.lmz.mike.dubbo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    private static Logger logger = LoggerFactory.getLogger("TestController");

    @Autowired
    private ITestService testService;

    @RequestMapping("/test")
    @ResponseBody
    public TestBean test(){

        String msg = "provider TestController:"+ Util.getMsg();
        logger.info(msg);
        System.out.println(msg);

        TestBean b = new TestBean();
        b.setId(1);
        b.setName("test");

        b = testService.test(b);

        return b;
    }

}
