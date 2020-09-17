package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.service.CatelogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CatalogController {

    @Reference
    CatelogService catelogService;


    @PostMapping("getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1() {

        List<PmsBaseCatalog1> pmsBaseCatalog1s = catelogService.getCatalog1();
        return pmsBaseCatalog1s;
    }


}
