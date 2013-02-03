package com.mmtzj.action;

import com.mmtzj.domain.Category;
import com.mmtzj.domain.Item;
import com.mmtzj.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-1-29
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
@RequestMapping("/wb")
@Controller
public class WBController {

    Logger logger = LoggerFactory.getLogger(WBController.class);

    @Resource
    private DataService dataService;

    @RequestMapping("/")
    public String index(Model model){
        long l1 = System.currentTimeMillis();
        List<Category> categories = dataService.getCategories();
        model.addAttribute("categories", categories);
        String pid = "";
        List<Item> items = dataService.getItems(pid);
        model.addAttribute("items", items);

        Map<String, Integer> itemTypes = dataService.getItemTypeCountsMap(items);
        model.addAttribute("itemTypes", itemTypes);
        long l2 = System.currentTimeMillis();
        logger.info("cost {} ms", l2 - l1);
        return "/wbapp/index";
    }
}
