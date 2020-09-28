package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.service.SkuService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SkuController {


    @Reference
    SkuService skuService;


    @RequestMapping("saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());

        skuService.saveSkuInfo(pmsSkuInfo);

        return "success";
    }

}
