package com.mmtzj.action;

import com.mmtzj.mapper.CategoryMapper;
import com.mmtzj.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-6
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/qqapp")
public class QQController {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ItemMapper itemMapper;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("categories", categoryMapper.getCategories());
        model.addAttribute("items", itemMapper.getAll());
        return "index";
    }

    @RequestMapping("/ilike")
    @ResponseBody
    public String ilike(HttpServletRequest request){
        System.out.println(1);
        return null;
    }

    @RequestMapping("/icollect")
    @ResponseBody
    public void icollect(HttpServletRequest request){
        System.out.println(2);
    }

    @RequestMapping("/iclick")
    @ResponseBody
    public void iclick(HttpServletRequest request){
        System.out.println(3);
    }

}
