package com.mmtzj.action;

import com.mmtzj.service.ItemService;
import com.mmtzj.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @Resource
    private ItemService itemService;

    @RequestMapping("/forAdd")
    public String forAddItem(HttpServletRequest request){
        return "/item/addItem";
    }

    @RequestMapping("/add")
    public String addItem(HttpServletRequest request){
        return "";
    }

    @RequestMapping("/list")
    public String listItem(HttpServletRequest request){

        return "/item/listItem";
    }

    @RequestMapping("/page")
    @ResponseBody
    public Page pageItem(HttpServletRequest request, Page page){
        itemService.findPage(page);
        return page;
    }
}
