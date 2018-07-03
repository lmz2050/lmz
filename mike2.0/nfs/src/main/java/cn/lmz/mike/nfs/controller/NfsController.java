package cn.lmz.mike.nfs.controller;

import cn.lmz.mike.common.web.response.JsonRes;
import cn.lmz.mike.nfs.service.NfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class FileController
{
    @Resource
    private NfsService nfsService;

    @Value("${nfs.upload.base.path}")
    private String nfs_upload_base_path;

    @RequestMapping("/upload/{subPath}")
    public JsonRes uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(name="subPath") String subPath)
    {
        return nfsService.upload(file,subPath);
    }

    @RequestMapping("/uploads")
    public Map<String, Object> uploadFiles(HttpServletRequest request)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        List<MultipartFile> files2 = ((MultipartHttpServletRequest) request).getFiles("file");
        BufferedOutputStream stream = null;
        String uploadPath = "upload/";
        validatePath(uploadPath);
        result.put("fileLength", files2.size());
        int i = 0;
        for (MultipartFile file : files2)
        {
            String originalFilename = file.getOriginalFilename();
            i++;
            validateType(uploadPath);
            if (!file.isEmpty())
            {
                try
                {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(uploadPath, originalFilename)));
                    stream.write(bytes);
                    result.put("file" + i, uploadPath + originalFilename);
                    stream.close();
                } catch (Exception e)
                {
                    result.put("message", originalFilename + "You failed to upload  => " + e.getMessage());
                }
            } else
            {
                result.put("message", originalFilename + "You failed to upload  because the file was empty.");
            }
        }
        return result;
    }

    /**
     * 验证上传的文件类型
     *
     * @return
     */
    private Map<String, Object> validateType(String path)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        return result;
    }

    /**
     * 验证上传的文件路径,没有则创建
     *
     * @return
     */
    private void validatePath(String path)
    {

        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    @RequestMapping("/showPushInfo")
    public List<PushMsgEntity> showAllPushInfo()
    {
        return pushRep.findAll(new Sort(Direction.DESC, "generateTime"));
    }
}