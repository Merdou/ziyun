package com.hxzy.controller.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * 通用的文件上传控制器
 */
@Log4j2
@Controller
@RequestMapping(value = "/common")
public class FileUploadController {

    @Value("${img.path}")
    private  String savePath;

    //文件保存目录URL
    @Value("${img.server}")
    private String saveUrl;

   private static  HashMap<String, String> extMap = new HashMap<String, String>();

   //静态构造函数赋值，文件上传类型
   static{
       //图片
       extMap.put("image", "gif,jpg,jpeg,png,bmp");
       //flash
       extMap.put("flash", "swf,flv");
       //音频、视频
       extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
       //其它文件
       extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
   }

    /**
     * 文件上传
     * @param imgFile
     * @param dir
     * @return
     */
   @ResponseBody
   @PostMapping(value = "/upload")
   public JSONObject fileUpload(MultipartFile imgFile,String dir){
       JSONObject  json=null;

       String tempSavePath=savePath;
       String tempSaveUrl=saveUrl;
       //检查目录
       File uploadDir = new File(tempSavePath);
       if(!uploadDir.isDirectory()){
           json=getError("上传目录不存在。") ;
           return json;
       }

      //检查目录写权限
       if(!uploadDir.canWrite()){
           return getError("上传目录没有写权限。");
       }

       //默认的文件夹为图片
       if (StringUtils.isBlank(dir)) {
           dir = "image";
       }

       //创建文件夹

       tempSavePath += dir + "/";
       tempSaveUrl += dir + "/";

       //判断文件夹是否存，如果不存在就创建一个
       File saveDirFile = new File(tempSavePath);
       if (!saveDirFile.exists()) {
           saveDirFile.mkdirs();
       }
       //再根据日期创建文件夹
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
       String ymd = sdf.format(new Date());
       tempSavePath += ymd + "/";
       tempSaveUrl += ymd + "/";
       File dirFile = new File(tempSavePath);
       if (!dirFile.exists()) {
           dirFile.mkdirs();
       }

       //检查文件大小
       long fileSize=imgFile.getSize();
       //10MB
       long maxSize=1024*1024*10;
       if(fileSize> maxSize){
           return getError("上传文件大小超过10MB限制。");
       }

       //检查扩展名
       String fileName=imgFile.getOriginalFilename();
       String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
       if(!Arrays.<String>asList(extMap.get(dir).split(",")).contains(fileExt)){
           return  getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dir) + "格式。");
       }

       //重新为文件 命名
       SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
       String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;

       try{
           //上传文件
           File uploadedFile = new File(tempSavePath, newFileName);
           //另存为
           imgFile.transferTo(uploadedFile);

           //向客户输出成功的消息
           json=new JSONObject();
           json.put("error", 0);
           json.put("url", tempSaveUrl + newFileName);

       }catch(Exception e){
           return getError("上传文件失败");
       }
       return  json;
   }


    private JSONObject getError(String s) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", s);
        return obj;
    }


    /**
     * webuploader文件上传
     * @param imgFile
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/fileupload")
    public JSONObject webFileUpload(@RequestParam(name = "file") MultipartFile imgFile){
        return  fileUpload(imgFile,"image");
    }



}
