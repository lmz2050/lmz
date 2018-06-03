package cn.lmz.mike.admin.business.action;

import cn.lmz.mike.admin.business.bean.Adm_dev_patch;
import cn.lmz.mike.admin.business.util.AdmMsg;
import cn.lmz.mike.admin.system.util.SysU;
import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.base.MapU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.page.Page;
import cn.lmz.mike.common.page.PageUtil;
import cn.lmz.mike.data.BeanUtil;
import cn.lmz.mike.data.bean.DataBean;
import cn.lmz.mike.data.bean.OrParams;
import cn.lmz.mike.data.util.LmzU;
import cn.lmz.mike.web.base.action.BaseAction;
import cn.lmz.mike.web.base.bean.BaseBean;
import cn.lmz.mike.web.base.bean.Lmzadmin;
import cn.lmz.mike.web.base.util.WebMsg;
import cn.lmz.mike.web.base.util.WebSV;
import cn.lmz.mike.web.base.util.WebU;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller()
@Scope("prototype")
public class PatchAction extends BaseAction {

    private static final Logger log = LoggerFactory.getLogger(PatchAction.class);

    private static final long serialVersionUID = 1L;
    protected Adm_dev_patch info = new Adm_dev_patch();
    private static final String UPLOADDIR = WebSV.getFileUploadPath();

    private String oem_type;         //客户名称
    private String deverce_name;     //设备名称 一代还是二代android手机还是IOS
    private String version;          //客户版本

    private String fileName;//下载文件命名
    private String name;

    public String update(){
        try {
            if(getInfo()!=null){
                Adm_dev_patch adp = null;
                List list = getwService().search(getInfo().getClass(),LmzU.getParams("vname", getInfo().getVname()));
                if(list!=null&&list.size()>0){
                    adp = (Adm_dev_patch)list.get(0);
                }
                BeanUtil.setBean(getInfo(), LmzU.getParams("uby",this.getAdmin().getUsername(),"utm", MC.date.getTimeString()));
                if(MC.string.isBlank(getInfo().getId())){
                    if(adp!=null&&getInfo().getVname().equalsIgnoreCase(adp.getVname())){
                        msg = WebMsg.getI18nMsg("admin.msg.admin_patch_vname_exists");
                        r.setSuccess(false);
                    }else {
                        BeanUtil.setBean(getInfo(), LmzU.getParams("cby", this.getAdmin().getUsername(), "ctm", MC.date.getTimeString()));
                        setInfo(getwService().create(getInfo()));
                        msg = "add";
                        r.setSuccess(true);
                    }
                }else{
                    if(adp!=null&&getInfo().getVname().equalsIgnoreCase(adp.getVname())&&!getInfo().getId().equalsIgnoreCase(adp.getId())){
                        msg = WebMsg.getI18nMsg("admin.msg.admin_patch_vname_exists");
                        r.setSuccess(false);
                    }else{
                        getwService().update(getInfo());
                        msg = "update";
                        r.setSuccess(true);
                    }
                }
                r.setMsg(msg);
            }
        } catch (LMZException e) {
            handException(e);
        }
        return WebSV.SUCCESS;
    }

    public String apage() {
        try {
            if(page==null||page<1)page=1;
            if(rows==null)rows=10;
            Page pg = new Page();
            pg.setIntPageSize(rows);
            pg.setIntCurrentPage(page);

            Lmzadmin adm = this.getAdmin();
            Map params = null;
            if (adm.getType() != 1) {
                params = MapU.getMap("cby", adm.getUsername());
            } else {
                params = new HashMap();
            }
            Map pageParams = getApageParams();
            pageParams.putAll(params);

            PageUtil pu = getwService().search(getInfo().getClass(), pageParams, pg, getDefOrd());

            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("total", pu.getPage().getIntRowCount());
            jsonMap.put("rows", pu.getList());

            return jsonStr(jsonMap);
        } catch (Exception e) {
            handException(e);
        }
        return WebSV.LOGIN;
    }

    public void checkUpdate() {

        try {
            String domain = SysU.getDomain(wService, m);
            String downUrl = domain + "api/update.action?id=";

            DataBean db = new DataBean(Adm_dev_patch.class);

            OrParams op = new OrParams().set("cby", oem_type).set("cby", "admin");
            db.put("cby_or_params", op);

            List list = wService.searchObj(db, null, null).getList();

            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Updatas><DownCount>"+ WebU.getDCount()+"</DownCount>");

            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Adm_dev_patch adp = (Adm_dev_patch) list.get(i);
                    adp.setUrl(downUrl + adp.getId());
                    sb.append(adp.toXmlString());
                }
            }
            sb.append("</Updatas>");

            HttpServletResponse response = this.getResponse();
            PrintWriter out = response.getWriter();
            response.setContentType("text/xml;charset=utf-8");
            response.setHeader("Cache-Control", "no-cache");
            out.write(sb.toString());
            out.flush();

        } catch (Exception e) {
            r.setSuccess(false);
            r.setMsg(e.getMessage());
            handException(e);
        }
    }

/*
    public InputStream getInputStream() throws Exception {
        try {

            Adm_dev_patch adp = wService.search(Adm_dev_patch.class, id);
            String fileFullName = UPLOADDIR + File.separator + adp.getUrl();
            this.fileName = "patch_" + adp.getId() + "_" + adp.getVname() + "_" + adp.getCby();

            log.info(oem_type + "download patch:" + fileFullName + "==>" + fileName);
            InputStream is = new FileInputStream(fileFullName);

            return is;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileName() {

        try {
            int i = fileName.lastIndexOf("/");
            fileName = fileName.substring(i + 1);
            fileName = new String(fileName.getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    public String execute() throws Exception {
        return "success";
    }
*/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String patch() {

        try {
            Adm_dev_patch adp = wService.search(Adm_dev_patch.class, id);

            name = adp.getUrl();
            fileName = "patch_" + adp.getId() + "_" + adp.getVname() + "_" + adp.getCby();
            fileName = java.net.URLEncoder.encode(fileName, "utf-8");

            return WebSV.SUCCESS;
            /*
            String fileFullName = UPLOADDIR + File.separator + adp.getUrl();
            String downname = "patch_" + adp.getId() + "_" + adp.getVname() + "_" + adp.getCby();

            log.info(oem_type + "download patch:" + fileFullName + "==>" + downname);
            InputStream is = new FileInputStream(fileFullName);

            //下载到哪里？客户端
            HttpServletResponse response = this.getResponse();
            OutputStream os = response.getOutputStream();
            //弹出下载的框filename:提示用户下载的文件名
            response.addHeader("content-disposition", "attachment;filename=" + java.net.URLEncoder.encode(downname, "utf-8"));

            byte[] b = new byte[1024];
            int size = is.read(b);
            while (size > 0) {
                os.write(b, 0, size);
                size = is.read(b);
            }
            is.close();
            os.close();
            */

        } catch (Exception e) {
            r.setSuccess(false);
            r.setMsg(e.getMessage());
            handException(e);
        }
        return null;
    }

    public Adm_dev_patch getInfo() {
        return info;
    }

    @Override
    protected void setInfo(BaseBean bean) {
        this.info = (Adm_dev_patch) bean;
    }

    public void setInfo(Adm_dev_patch info) {
        this.info = info;
    }


    public String getOem_type() {
        return oem_type;
    }

    public void setOem_type(String oem_type) {
        this.oem_type = oem_type;
    }

    public String getDeverce_name() {
        return deverce_name;
    }


    public void setDeverce_name(String deverce_name) {
        this.deverce_name = deverce_name;
    }


    public String getVersion() {
        return version;
    }


    public void setVersion(String version) {
        this.version = version;
    }


}
