package com.mmtzj.action;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mmtzj.domain.Category;
import com.mmtzj.domain.Eval;
import com.mmtzj.domain.Item;
import com.mmtzj.mapper.CategoryMapper;
import com.mmtzj.mapper.IcollectMapper;
import com.mmtzj.mapper.IlikeMapper;
import com.mmtzj.mapper.ItemMapper;
import com.mmtzj.service.JedisService;
import com.mmtzj.service.QQService;
import com.mmtzj.util.BaseUtil;
import com.mmtzj.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: wangxin
 * Date: 13-1-6
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/qqapp")
public class QQController extends BaseController{

    Logger logger = LoggerFactory.getLogger(QQController.class);

    @Resource
    private QQService qqService;

    @Resource
    private JedisService jedisService;

    @RequestMapping("/")
    public String index(Model model){
        long l1 = System.currentTimeMillis();
        List<Category> categories = (List<Category>) jedisService.get("categories");
        if(categories == null){
            categories = qqService.getCategories();
            jedisService.set("categories", categories, Constant.CACHE_SECONDS);
        }
        model.addAttribute("categories", categories);
        List<Item> items = (List<Item>) jedisService.get("items");
        if(items == null){
            items = qqService.getAllItems();
            jedisService.set("items", items, Constant.CACHE_SECONDS);
        }
        model.addAttribute("items", items);

        Map<String, Integer> itemTypes = Maps.newHashMap();
        for(Item item : items){
            List<Eval> evals = (List<Eval>) jedisService.get(item.getId() + "evals");
            if(evals == null){
                evals = qqService.getAllEvals(ImmutableList.of(item.getId()));
                jedisService.set(item.getId() + "evals", evals, Constant.CACHE_SECONDS);
            }
            item.setEvalList(evals);
            if(itemTypes.containsKey("itemtype"+item.getCategoryId())){
                itemTypes.put("itemtype"+item.getCategoryId(), itemTypes.get("itemtype"+item.getCategoryId())+1);
            }else{
                itemTypes.put("itemtype"+item.getCategoryId(), 1);
            }
        }
        model.addAttribute("itemTypes", itemTypes);
        long l2 = System.currentTimeMillis();
        logger.info("cost {} ms", l2 - l1);
        return "index";
    }

    @RequestMapping("/ilike")
    @ResponseBody
    public String ilike(HttpServletRequest request){
        int itemId = BaseUtil.getIntValue(request.getParameter("iid"));
        logger.info("the ip {} like the item {}", BaseUtil.getIP(request), itemId);
        qqService.updateItem(itemId, "likeCount");
        return null;
    }

    @RequestMapping("/icollect")
    @ResponseBody
    public String icollect(HttpServletRequest request){
        int itemId = BaseUtil.getIntValue(request.getParameter("iid"));
        logger.info("the ip {} collect the item {}", BaseUtil.getIP(request), itemId);
        qqService.updateItem(itemId, "collectCount");
        return null;
    }

    @RequestMapping("/iclick")
    @ResponseBody
    public String iclick(HttpServletRequest request){
        int itemId = BaseUtil.getIntValue(request.getParameter("iid"));
        logger.info("the ip {} click the item {}", BaseUtil.getIP(request), itemId);
        qqService.updateItem(itemId, "wantToBuy");
        return null;
    }

}
