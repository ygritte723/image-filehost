package cn.zxl.filecloudplus.controller;


import cn.zxl.filecloudplus.entity.FileHost;
import cn.zxl.filecloudplus.entity.ImageHost;
import cn.zxl.filecloudplus.entity.UserIp;
import cn.zxl.filecloudplus.service.FileHostService;
import cn.zxl.filecloudplus.service.UserIpService;
import cn.zxl.filecloudplus.util.base64Encode;
import cn.zxl.filecloudplus.util.getUserIP;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Controller
@RequestMapping("/fileHost")
public class FileHostController {
    @Autowired
    private FileHostService fileHostService;
    @Autowired
    private UserIpService userIpService;

    @CrossOrigin
    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam(value = "fileType") String fileType,
                             @RequestParam(value = "fileTypeDetail") String fileTypeDetail,
                             @RequestParam("theUserName") String username){
        // 判断具体分类
        if(fileType==null){
            fileType="Others";
        }
        FileHost fileHost=new FileHost();
        // 获取原始名字
        String fileName = file.getOriginalFilename();
        fileHost.setFileName(fileName);
        fileHost.setId(String.valueOf(IdWorker.getId()));
        fileHost.setFileType(fileType);
        fileHost.setFileTypeDetail(fileTypeDetail);
        // 获取文件大小
        double fileSize=file.getSize();
        fileHost.setFileSize(fileSize);
        // 文件保存路径
        String filePathSql=null;
        if(fileTypeDetail!=null) {
            filePathSql = "/fileHostFile/" + fileType + "/" + fileTypeDetail + "/" + UUID.randomUUID() + "-" + fileName;
        }
        else {
            filePathSql = "/fileHostFile/" + fileType  + "/" + UUID.randomUUID() + "-" + fileName;
        }
        fileHost.setPath(filePathSql);
        fileHost.setUploadUser(username);
        // 文件重命名，防止重复
        fileName = System.getProperty("user.dir")+"/file" + filePathSql;
        // 文件对象
        File dest = new File(fileName);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(dest);
            fileHostService.save(fileHost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "200";
    }

    @GetMapping(value = "/download/{id}")
    public void download(@PathVariable(value = "id", required = false) String id, HttpServletResponse response, HttpServletRequest request) {
        id= base64Encode.reverseBase64(id);
        try {
            File file = new File(System.getProperty("user.dir")+"/file"+fileHostService.getById(id).getPath());
            // 穿件输入对象
            FileInputStream fis = new FileInputStream(file);
            // 设置相关格式
            response.setContentLengthLong((long) Math.ceil(fileHostService.getById(id).getFileSize()));
            response.setContentType("application/octet-stream");
            // 设置下载后的文件名以及header
            response.setHeader("Content-disposition", "attachment;fileName=" + new String(fileHostService.getById(id).getFileName().getBytes("UTF-8"),"iso-8859-1"));
            // 创建输出对象
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            fis.close();
            os.close();
            FileHost fileHost=fileHostService.getById(id);
            fileHost.setDownloadCount(fileHost.getDownloadCount()+1);
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("id",id);
            fileHostService.update(fileHost,wrapper);
            //记录IP
            UserIp userIp=new UserIp();
            userIp.setTargetClassify(fileHost.getFileType()+"-"+fileHost.getFileTypeDetail());
            userIp.setTargetId(id);
            userIp.setUserIp(getUserIP.getIpAddr(request));
            userIpService.save(userIp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id", required = false) String id){
        id=base64Encode.reverseBase64(id);
        File file = new File(System.getProperty("user.dir")+"/file"+fileHostService.getById(id).getPath());
        if(file.exists()){
            //如果文件存在，则在数据库内逻辑删除
            fileHostService.removeById(id);
            return "Success";
        }else{
            System.out.println("文件删除失败！");
            return "Fail";
        }
    }

}

