package cn.lmz.mike.web.base.upload;

import cn.lmz.mike.web.base.action.BasicAction;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;


@Controller()
@Scope("prototype")
public class ProgressAction extends BasicAction {
    private Logger log = LoggerFactory.getLogger(ProgressAction.class);
    @Override
    public String execute() throws Exception {
        //System.out.println("获取上传文件进度信息");
        Map jsonData = new HashMap<>();//初始化jsonData!!!!
        FileUploadProgress p;
        Object attribute = ServletActionContext.getRequest().getSession().getAttribute("fileUploadProgress");
        if(null == attribute){
            jsonData.put("completed", false);
            jsonData.put("isStarted", false);

            return jsonStr(jsonData);
        }else{
            p = (FileUploadProgress)attribute;
        }
        long time = (System.currentTimeMillis() - p.getStartTime())/ 1000 + 1; //已传输的时间 单位:s
        double v = ((double)p.getCurrentLength()) / (double)time; // b/s
        int percent = (int)(100 * (double)p.getCurrentLength()/ (double)p.getTotalLength());

        jsonData.put("percent",percent);
        jsonData.put("remain", (int)(p.getTotalLength()/v-time));
        jsonData.put("isStarted", true);
        jsonData.put("completed", p.isFlag());
        if (p.isFlag()){
            log.info("已完成，重置fileUploadProgress");
            ServletActionContext.getRequest().getSession().removeAttribute("fileUploadProgress");
        }

        return jsonStr(jsonData);
    }

}