package com.atguigu.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.service.SpuService;
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

    @Reference
    SpuService spuService;


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
        //sku对象
        modelMap.put("skuInfo",pmsSkuInfo);

        List<PmsProductSaleAttr> pmsProductSaleAttrList = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId());
        //销售属性列表
        modelMap.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrList);
        return "item";
    }


}
