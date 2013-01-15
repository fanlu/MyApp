package com.mmtzj.action;

import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-12
 * Time: 下午7:28
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class UploadController {

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest fileRequest = (MultipartHttpServletRequest)request;
        List<MultipartFile> files = fileRequest.getFiles("qqfile");
        ResourceBundle rb = ResourceBundle.getBundle("file");
        String filePath = rb.getString("file.path") + File.separator + new DateTime().toString("yyyyMMdd") + File.separator;
        File pathFile = new File(filePath);
        if(!pathFile.exists()){
            pathFile.mkdirs();
        }
        for (MultipartFile myfile : files){
            String path = myfile.getOriginalFilename();
            String newName = Long.toHexString(new Date().getTime()) + path.substring(path.lastIndexOf("."));
            InputStream is = null;
            try {
                is = myfile.getInputStream();
                File destFile = new File(filePath +newName);
                IOUtils.copy(is, new FileOutputStream(destFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "{\"success\": true}";
    }
}
