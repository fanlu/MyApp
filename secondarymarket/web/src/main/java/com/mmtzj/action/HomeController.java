package com.mmtzj.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-14
 * Time: 下午10:03
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String index(Model model){
        return "redirect:/wb/";
    }
}
