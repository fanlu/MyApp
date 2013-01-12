package com.mmtzj.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-12
 * Time: 下午7:55
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @RequestMapping("/add")
    public String addItem(HttpServletRequest request){
        return "/item/addItem";
    }
}
