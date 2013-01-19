package com.mmtzj.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-19
 * Time: 上午8:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public String index(){
        return "/index";
    }
}
