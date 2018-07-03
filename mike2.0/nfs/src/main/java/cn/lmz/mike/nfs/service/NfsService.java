package cn.lmz.mike.nfs.service;

import cn.lmz.mike.common.web.response.JsonRes;
import org.springframework.web.multipart.MultipartFile;

public interface NfsService {

    public JsonRes upload(MultipartFile file,String subPath);


}
