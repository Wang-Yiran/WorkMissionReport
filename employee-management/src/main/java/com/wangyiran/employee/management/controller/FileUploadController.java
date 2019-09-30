package com.wangyiran.employee.management.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: employee-management
 * @description:
 * @author: Mr.Wang
 * @create: 2019-09-29 16:38
 **/
//@Controller //仅测试使用
public class FileUploadController {
    //指定一个临时路径作为上传目录
    //private static String UPLOAD_FOLDER = "C:\\Users\\Desktop\\UPLOAD\\"
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String uploadIndex(){
        return "/file/upload";
    }
    @PostMapping("upload")
    public String fileUpload(@RequestParam("file") MultipartFile srcFile, RedirectAttributes redirectAttributes) throws Exception{
        //前端没有选择文件，srcFile为空
        if (srcFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个文件");
            return "redirect:upload_status";
        }
        //选择了文件，开始上传操作
        try {
            //构建上传目标路径，找到了项目的target的classes目录
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());
            if (!destFile.exists()) {
                destFile = new File("");
            }
            //输出目标文件的绝对路径
            System.out.println("file path:" + destFile.getAbsolutePath());
            //拼接子路径，以时间日期创建文件夹
            Date date = new Date();
            File upload = new File(destFile.getAbsolutePath(), "static/" + new SimpleDateFormat("yyyyMMdd/").format(date));
            //若目标文件夹不存在，则创建
            if (!upload.exists()) {
                upload.mkdirs();
            }
            //使用uuid重命名文件
            String fileName = srcFile.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            System.out.println("完整的上传路径：" + upload.getAbsolutePath() + "/" + fileExtension);
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = srcFile.getBytes();
            //拼接上传路径
            //Path path = Paths.get(UPLOAD_FOLDER + srcFile.getOriginalFilename());
            //通过项目路径，拼接上传路径
            Path path = Paths.get(upload.getAbsolutePath() + "/" + srcFile.getOriginalFilename() + System.currentTimeMillis() + fileExtension);
            //** 开始将源文件写入目标地址
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message", "文件上传成功" + fileExtension);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:upload_status";
    }

    //匹配upload_status页面
    @GetMapping("/upload_status")
    public String uploadStatusPage() {
        return "file/upload_status";
    }
}
