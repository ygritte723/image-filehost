package cn.zxl.filecloudplus.controller;


import cn.zxl.filecloudplus.entity.ImageHost;
import cn.zxl.filecloudplus.entity.UserAdmin;
import cn.zxl.filecloudplus.entity.UserIp;
import cn.zxl.filecloudplus.service.ImageTypeService;
import cn.zxl.filecloudplus.service.UserAdminService;
import cn.zxl.filecloudplus.util.base64Encode;
import cn.zxl.filecloudplus.util.getUserIP;
import cn.zxl.filecloudplus.util.saveAvatar;
import cn.zxl.filecloudplus.util.sendEmailTool;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Controller
@RequestMapping("/userAdmin")
public class UserAdminController {
    @Autowired
    private UserAdminService adminService;
    @Autowired
    private ImageTypeService imageTypeService;
    @Autowired
    private sendEmailTool emailTool;
    private UserAdmin userAdmin=new UserAdmin();

//    访问管理员管理页面
    @GetMapping("/admin")
    public String admin(HttpServletRequest req,Model model) {
        model.addAttribute("imageTypes", imageTypeService.list());
        HttpSession session = req.getSession();
        if(!session.getAttribute("userRole").equals(99)){
            return "manager/consumer";
        }
        return "manager/admin";
    }

    @PostMapping("/enter")
    public String enter(HttpServletRequest req, @RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password) {
        QueryWrapper wrapper = new QueryWrapper();
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        wrapper.allEq(map);
        UserAdmin userAdmin=null;
        userAdmin=adminService.getOne(wrapper);
        if (userAdmin!=null) {
            HttpSession session = req.getSession();
            session.setAttribute("sessionEmail",userAdmin.getUserEmail());
            session.setAttribute("sessionUser", userAdmin.getUsername());
            session.setAttribute("userRole",userAdmin.getUserRole());
            return "index";
        }
        return "manager/login";

    }

    // 拦截登录请求到登录页面
    @RequestMapping("/login")
    public String login(){
        return "manager/login";
    }

    // 注册请求
    @RequestMapping("/register")
    public String register(){
        return "manager/register";
    }

    // 注册信息验证
    @PostMapping("/register")
    public String Register(@RequestParam(value = "userEmail") String email,
                           @RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "code") String code ,
                           HttpServletRequest req,
                           Model model){
        HttpSession session=req.getSession();
        userAdmin.setUserEmail(email);
        userAdmin.setUsername(username);
        userAdmin.setPassword(password);
        if(!code.equals(session.getAttribute("sessionCode"))){
            model.addAttribute("status","Error");
            model.addAttribute("tips","验证密码不一致");
        }
        else if(!adminService.findUser(username,email)){
            model.addAttribute("status","Error");
            model.addAttribute("tips","用户名或邮箱已经被使用");
        }
        else {
            model.addAttribute("status","Success");
            userAdmin.setAvatarPath(saveAvatar.saveAvatar(email,username));
            model.addAttribute("tips","注册成功，请返回登录界面登录");
            adminService.save(userAdmin);
        }
        model.addAttribute("span01","返回登录");
        model.addAttribute("a01","/userAdmin/login");
        model.addAttribute("span02","返回主页");
        model.addAttribute("a02","/");
        return "sundry/tips";
    }

    // 获取验证码
    @GetMapping("/email")
    @ResponseBody
    public String sendEmail(HttpServletRequest req,@RequestParam(value = "email",required = false) String email){
        if(email==null){
            return "非法请求嗷";
        }
        String code=null;
        code=emailTool.sendVerifyEmail(email);
        if (code==null){
            return "非法请求嗷";
        }
        HttpSession session = req.getSession();
        session.setAttribute("sessionCode", code);
        session.setMaxInactiveInterval(60*10);
        return "Success";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req){
        req.getSession().invalidate();
        return "index";

    }

    @GetMapping(value = "/getAvatar/{userEmail}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[]  getImage(@PathVariable(value = "userEmail", required = false) String userEmail, HttpServletRequest req) throws IOException {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_email",userEmail);
        UserAdmin user=adminService.getOne(wrapper);
        try (InputStream is = new FileInputStream(System.getProperty("user.dir")+user.getAvatarPath())){
            byte[] bytes = new byte[is.available()];
            is.read(bytes, 0, is.available());
            is.close();
            return bytes;
        }
    }

}

