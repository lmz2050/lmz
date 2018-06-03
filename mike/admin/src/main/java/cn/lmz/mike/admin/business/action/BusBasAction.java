package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.common.base.MapU;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.bean.Return;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public abstract class BusBasAction extends BaseAction {



    protected Map getQyParams(){
        Lmzadmin adm = this.getAdmin();
        Map params = null;
        if(adm.getType()!=1){
            params = MapU.getMap("cus_name",adm.getUsername());
        }else{
            params = new HashMap();
        }
        return params;
    }

    protected void printXML(String xml) throws IOException {
        HttpServletResponse response = this.getResponse();
        PrintWriter out = response.getWriter();
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        out.write(xml);
        out.flush();
    }

    protected void printXML(Return re) throws IOException {
        HttpServletResponse response = this.getResponse();
        PrintWriter out = response.getWriter();
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        if(re.isSuccess()){
            out.write(re.getObj().toString());
        }else{
            out.write(re.getXML());
        }
        out.flush();
    }


}
