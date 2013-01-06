package com.fl.action;

import com.fl.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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


    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("categories", categoryMapper.getCategories());
        return "index";
    }

}
