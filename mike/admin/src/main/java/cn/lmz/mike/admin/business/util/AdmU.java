package cn.lmz.mike.admin.business.util;


import cn.lmz.mike.common.MC;
import cn.lmz.mike.common.base.StrU;
import cn.lmz.mike.common.exception.LMZException;
import cn.lmz.mike.common.mail.MailU;
import cn.lmz.mike.web.base.service.IWService;
import cn.lmz.mike.web.bean.Lmzweb;

import java.util.List;

public class AdmU {

    public static Integer getInteger(String str){
        if(StrU.isBlank(str)) return 0;
        return new Integer(str);
    }
    public static Integer addInteger(Integer d,String str){
        if(d==null) d = 0;
        return getInteger(str)+d;
    }

    public static String getMailFrom(IWService service) throws LMZException {
        String mailfrom = (String)MC.cache.get(MailU.MAIL_FROM);
        if(MC.string.isEmpty(mailfrom)){
            List list = service.search(Lmzweb.class);
            if(list!=null&&list.size()>0){
                Lmzweb web = (Lmzweb) list.get(0);
                mailfrom = web.getMailfromhost()+"|"+web.getMailfromuname()+"|"+web.getMailfrompwd();
                MC.cache.put(MailU.MAIL_FROM,mailfrom);
                MC.cache.put(MailU.MAIL_TO,web.getMailtohost());
            }
        }
        return mailfrom;
    }
    public static String getMailTo(IWService service) throws LMZException {
        String mailto = (String)MC.cache.get(MailU.MAIL_TO);
        if(MC.string.isEmpty(mailto)){
            List list = service.search(Lmzweb.class);
            if(list!=null&&list.size()>0){
                Lmzweb web = (Lmzweb) list.get(0);
                String mailfrom = web.getMailfromhost()+"|"+web.getMailfromuname()+"|"+web.getMailfrompwd();
                mailto = web.getMailtohost();
                MC.cache.put(MailU.MAIL_FROM,mailfrom);
                MC.cache.put(MailU.MAIL_TO,mailto);
            }
        }
        return mailto;
    }


}
