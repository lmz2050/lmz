package cn.lmz.mike.nfs.service.impl;

import cn.lmz.mike.common.web.response.JsonRes;
import cn.lmz.mike.nfs.service.NfsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class NfsServiceImpl implements NfsService{
    @Override
    public JsonRes upload(MultipartFile file, String subPath) {
        if (file.isEmpty())
        {
            return JsonRes.e "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = null;
        try
        {
            filePath = new File("").getCanonicalPath() + "/tmp/uploadFile/";
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
        String tagFilePath = filePath + CommonUtil.getCurrentTime() + fileName;
        System.out.println("存储路径为:" + tagFilePath);
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(tagFilePath);
        // 检测是否存在目录
        if (!dest.getParentFile().exists())
        {
            dest.getParentFile().mkdirs();
        }
        try
        {
            file.transferTo(dest);
            PushMsgEntity pushInfo=new PushMsgEntity();
            pushInfo.setFilePath(fileName);
            pushInfo.setUserName("深圳");
            pushRep.save(pushInfo);
            return fileName + "推送成功";
        } catch (IllegalStateException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileName + "推送失败";
    }
}
