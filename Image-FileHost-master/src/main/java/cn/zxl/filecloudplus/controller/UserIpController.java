package cn.zxl.filecloudplus.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zxl
 * @since 2021-12-17
 */
@Controller
@RequestMapping("/support")
public class UserIpController {

    @RequestMapping("/test")
    public String tips(){
        return "sundry/tips";
    }
}

