package cn.lmz.mike.admin.system.controller;

import cn.lmz.mike.common.web.response.JsonRes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value ="/system" )
public class SystemController {


    private <T> T getRequestVO(String table,Map<String,Object> params){



    }


    @RequestMapping(value = "/{table}/update")
    @ResponseBody
    public JsonRes update(@RequestParam Map<String,Object> params,@PathVariable(name="table") String table){



        return JsonRes.success();
    }



}
