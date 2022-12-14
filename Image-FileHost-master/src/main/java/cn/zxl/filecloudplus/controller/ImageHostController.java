package cn.zxl.filecloudplus.controller;


import cn.zxl.filecloudplus.entity.ImageHost;
import cn.zxl.filecloudplus.entity.UserIp;
import cn.zxl.filecloudplus.service.ImageHostService;
import cn.zxl.filecloudplus.service.UserIpService;
import cn.zxl.filecloudplus.util.base64Encode;
import cn.zxl.filecloudplus.util.getUserIP;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Controller
@RequestMapping("/imageHost")
public class ImageHostController {

    @Autowired
    private ImageHostService imageHostService;
    @Autowired
    private UserIpService userIpService;

    @CrossOrigin
    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file,@RequestParam(value = "imageType",required = false) String imageType) {
        ImageHost imageHost=new ImageHost();
        // 设置图片名字
        String imageName=file.getOriginalFilename();
        imageHost.setImageName(imageName);
        // 设置图片对象类别
        imageHost.setImageType(imageType);
        // 获取图片大小
        imageHost.setFileSize(file.getSize());
        // 图片防止重复-提前设置图片雪花ID
        imageHost.setId(String.valueOf(IdWorker.getId(imageHost)));
        // 重命名原图片，以雪花算法重命名
        String newImgName=imageHost.getId()+"-"+ imageName;
        // 原图存储路径
        String imgPathSql="/imageHostFile/original/"+imageType+"/"+newImgName;
        imageHost.setPath(imgPathSql);
        // 缩略图重命名
        newImgName=newImgName.substring(0,imageName.lastIndexOf("."))+".webp";
        // 缩略图存储路径
        String thumbnailPathPathSql="/imageHostFile/thumbnail/"+imageType+"/"+newImgName;
        imageHost.setThumbnailPath(thumbnailPathPathSql);
        // 文件存储地址和名字
        String originalImagePath = System.getProperty("user.dir")+"/file" + imgPathSql;
        String thumbnailImagePath= System.getProperty("user.dir")+"/file" + thumbnailPathPathSql;
        imageHost.setUploadUser("Zxl");
        File originalImage = new File(originalImagePath);
        File thumbnailImage=new File(thumbnailImagePath);
        // 判断路径是否存在，如果不存在则创建
        if (!originalImage.getParentFile().exists()) {
            originalImage.getParentFile().mkdirs();
        }
        try {
            // 保存到服务器中
            file.transferTo(originalImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!thumbnailImage.getParentFile().exists()) {
            thumbnailImage.getParentFile().mkdirs();
        }
        try {
            Thumbnails.of(originalImage).size(328,656)
                    .imageType(ThumbnailParameter.DEFAULT_IMAGE_TYPE)
                    .outputQuality(0.85f)
                    .outputFormat("webp")
                    .toFile(thumbnailImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageHostService.save(imageHost);
        return "200";
    }

    @GetMapping(value = "/getImage/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[]  getImage(@PathVariable(value = "id", required = false) String id, HttpServletRequest req) throws IOException {
        id= base64Encode.reverseBase64(id);
        ImageHost imageHost=imageHostService.getById(id);
        if(imageHost==null){
            imageHost=imageHostService.getById(404);
            id="404";
        }
        try (InputStream is = new FileInputStream(System.getProperty("user.dir")+"/file"+imageHost.getPath())){
            byte[] bytes = new byte[is.available()];
            is.read(bytes, 0, is.available());

            imageHost.setDownloadCount(imageHost.getDownloadCount()+1);
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("id",id);
            imageHostService.update(imageHost,wrapper);
            //记录IP
            UserIp userIp=new UserIp();
            userIp.setTargetClassify("ImageHost");
            userIp.setTargetId(id);
            userIp.setUserIp(getUserIP.getIpAddr(req));
            userIpService.save(userIp);
            is.close();
            return bytes;
        }
    }

    @GetMapping(value = "/download/{id}")
    public void download(@PathVariable(value = "id", required = false) String id, HttpServletResponse response, HttpServletRequest request) {
        id= base64Encode.reverseBase64(id);
        try {
            File file = new File(System.getProperty("user.dir")+"/file"+imageHostService.getById(id).getPath());
            // 穿件输入对象
            FileInputStream fis = new FileInputStream(file);
            // 设置相关格式
            response.setContentType("application/force-download");
            // 设置响应下载文件大小
            response.setContentLengthLong((long) imageHostService.getById(id).getFileSize());
            // 设置下载后的文件名以及header(UTF-8)
            response.addHeader("Content-disposition", "attachment;fileName=" + new String(imageHostService.getById(id).getImageName().getBytes("UTF-8"),"iso-8859-1"));
            // 创建输出对象
            OutputStream os = response.getOutputStream();
            // 常规操作
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            fis.close();
            ImageHost imageHost=imageHostService.getById(id);
            imageHost.setDownloadCount(imageHost.getDownloadCount()+1);
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("id",id);
            imageHostService.update(imageHost,wrapper);
            //记录IP
            UserIp userIp=new UserIp();
            userIp.setTargetClassify("ImageHost");
            userIp.setTargetId(id);
            userIp.setUserIp(getUserIP.getIpAddr(request));
            userIpService.save(userIp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/imageDetail/{id}")
    public String getDetail(@PathVariable(value = "id", required = false) String id, Model model){
        ImageHost imageHost=imageHostService.getById(base64Encode.reverseBase64(id));
        imageHost.setId(id);
        model.addAttribute("imgDetail",imageHost);
        return "ih/imageDetail";
    }

    @GetMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable(value = "id", required = false) String id){
        id= base64Encode.reverseBase64(id);
        File file = new File(System.getProperty("user.dir")+"/file"+imageHostService.getById(id).getPath());
        if(file.exists()){
            imageHostService.removeById(id);
            return "Success";
        }else{
            System.out.println("文件删除失败！");
            return "Fail";
        }
    }

    @RequestMapping("/test")
    public String test(){
        return "upload";
    }

}

