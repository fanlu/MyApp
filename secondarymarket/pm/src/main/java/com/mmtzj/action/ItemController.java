package com.mmtzj.action;

import com.mmtzj.FormBean.ItemForm;
import com.mmtzj.domain.Category;
import com.mmtzj.domain.Item;
import com.mmtzj.mapper.CategoryMapper;
import com.mmtzj.service.ItemService;
import com.mmtzj.util.Page;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Resource
    private CategoryMapper categoryMapper;

    @RequestMapping("/forAdd")
    public String forAddItem(HttpServletRequest request, Model model){
        List<Category> categories = categoryMapper.getCategories();
        model.addAttribute("categories", categories);
        return "/item/addItem";
    }

    @RequestMapping("/forUpdate/{id}")
    public String forUpdateItem(HttpServletRequest request, Model model, @PathVariable Integer id){
        List<Category> categories = categoryMapper.getCategories();
        model.addAttribute("categories", categories);
        Item item = itemService.get(id);
        model.addAttribute("item", item);
        return "/item/addItem";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String saveItem(HttpServletRequest request, ItemForm itemForm) throws Exception {
//        itemService.saveOrUpdate(item);
        Item item = new Item();
        PropertyUtils.copyProperties(item, itemForm);
        return "success";
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
