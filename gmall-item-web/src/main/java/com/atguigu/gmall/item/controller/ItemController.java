package com.atguigu.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.service.SkuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Controller
public class ItemController {

    @Reference
    SkuService skuService;


    @RequestMapping("index")
    public String index(ModelMap modelMap) {
        modelMap.put("hello","hello wldd");
        List<String> list = Arrays.asList("a","b","c");
        modelMap.put("list",list);
        return "index";
    }

    @GetMapping("item/{skuId}")
    public String item(@PathVariable String skuId,ModelMap modelMap) {

        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);
        modelMap.put("skuInfo",pmsSkuInfo);
        return "item";
    }


}
