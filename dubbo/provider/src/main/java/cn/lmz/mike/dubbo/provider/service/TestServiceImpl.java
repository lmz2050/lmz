package cn.lmz.mike.dubbo.provider.service;


import cn.lmz.mike.dubbo.api.bean.TestBean;
import cn.lmz.mike.dubbo.api.service.ITestService;
import cn.lmz.mike.dubbo.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@Service
public class TestServiceImpl implements ITestService {

    private static Logger logger = LoggerFactory.getLogger("TestServiceImpl");

    @Override
    public TestBean test(TestBean b) {
        b.setId(10);
        b.setName(Util.getMsg());

        String msg = "TestServiceImpl:"+b.getId()+"--"+b.getName();
        logger.info(msg);
        System.out.println(msg);

        return b;
    }


    public static void main(String[] args) {
        TestServiceImpl t = new TestServiceImpl();
        TestBean b = new TestBean();
        b = t.test(b);

        System.out.println(b.getId()+"--"+b.getName());

    }

}
