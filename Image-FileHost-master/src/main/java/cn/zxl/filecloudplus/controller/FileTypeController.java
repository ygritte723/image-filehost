package cn.zxl.filecloudplus.controller;


import cn.zxl.filecloudplus.entity.FileHost;
import cn.zxl.filecloudplus.service.FileHostService;
import cn.zxl.filecloudplus.service.FileTypeService;
import cn.zxl.filecloudplus.util.base64Encode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Controller
@RequestMapping("/fileType")
public class FileTypeController {
    @Autowired
    private FileTypeService fileTypeService;
    @Autowired
    private FileHostService fileHostService;
    private List <FileHost> list=null;

    @RequestMapping
    public String select(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "page", required = false) String page,
                         HttpServletRequest req, @RequestParam(value = "detailType", required = false) String detailType) {
        if (page == null) {
            page = "1";
        }
        // 一级分类判断
        if (type == null) {
            type = "Others";
        }
        model.addAttribute("type", type);
        // 二级分类查询
        List <String> detailTypeList=null;
        detailTypeList=fileTypeService.getDetailByType(type);
        model.addAttribute("filetype",detailTypeList);
        // 二级分类具体内容通过session给前端交互
        model.addAttribute("detailType", detailType);

        HttpSession session = req.getSession();
        session.setAttribute("dataPrePage", 8);
        session.setAttribute("currentPage", page);
        // 判断总页数，detailType为细则分类
        if(detailType != null && !detailType.equals("") ){
            session.setAttribute("pages", fileHostService.getPages(null, detailType));
        }
        else {
            session.setAttribute("pages", fileHostService.getPages(type));
        }

        if (detailType != null && !detailType.equals("")) {
            list= fileHostService.FindFiles(Integer.parseInt(page), type, detailType);
            list.forEach(item -> {
                String temp= String.valueOf(item.getId());
                temp= base64Encode.convertToBase64(temp);
                item.setId(temp);
            });
            model.addAttribute("fileList",list);
            return "fh/fileByType";
        }
        list=fileHostService.FindFiles(Integer.parseInt(page), type);
        list.forEach(item -> {
            String temp= String.valueOf(item.getId());
            temp= base64Encode.convertToBase64(temp);
            item.setId(temp);
        });
        model.addAttribute("fileList", list);
        if(!type.equals("Others")){
            return "fh/fileByType";
        }
        return "fh/fileByName";
    }

    @RequestMapping("/dataInfo")
    public String dataInfo(Model model, @RequestParam(value = "type", required = false) String type,
                           HttpServletRequest req, @RequestParam(value = "detailType", required = false) String detailType) {
        // 一级分类
        if (type == null) {
            type = "Others";
        }
        // 二级分类查询
        List <String> detailTypeList=null;
        detailTypeList=fileTypeService.getDetailByType(type);
        // 文件总大小
        double fileSizeTotal=0.00;
        // 文件下载次数统计
        int fileDownloadTotal;
        QueryWrapper wrapper = new QueryWrapper();
        if(detailType!=null){
            wrapper.eq("file_Type_Detail", detailType);
            wrapper.eq("file_type",type);
        }
        else {
            wrapper.eq("file_Type", type);
        }

        if(fileTypeService.getType().contains(type)){
            model.addAttribute("filetype", detailTypeList);
        }

        List <FileHost> fileList=fileHostService.list(wrapper);
        fileDownloadTotal = fileList.stream().collect(Collectors.summingInt(FileHost::getDownloadCount));
        for (FileHost temp : fileList) {
            fileSizeTotal=fileSizeTotal+temp.getFileSize()*temp.getDownloadCount();
        }
        model.addAttribute("type",type);
        model.addAttribute("fileList", fileHostService.list(wrapper));
        model.addAttribute("fileSizeTotal", fileSizeTotal);
        model.addAttribute("detailType", detailType);
        model.addAttribute("fileDownloadTotal", fileDownloadTotal);
        return "fh/dataInfo";
    }


    @CrossOrigin
    @GetMapping("/fileTypeDetail/{fileType}")
    @ResponseBody
    public List<String> getFileTypeDetail(@PathVariable(value = "fileType") String fileType){
        List fileTypeDetial=fileTypeService.getDetailByType(fileType);
        System.out.println(fileTypeDetial.toString());
        return fileTypeDetial;
    }

}

