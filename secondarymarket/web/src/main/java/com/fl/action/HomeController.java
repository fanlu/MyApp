package com.fl.action;

import com.fl.mapper.CategoryMapper;
import com.fl.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-6
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class HomeController {

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
    public void ilike(HttpServletRequest request){
        System.out.println(1);
    }

    @RequestMapping("/icollect")
    public void icollect(HttpServletRequest request){
        System.out.println(2);
    }

    @RequestMapping("/iclick")
    public void iclick(HttpServletRequest request){
        System.out.println(3);
    }

}
