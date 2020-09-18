package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;
import com.atguigu.gmall.service.CatelogService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        List<PmsBaseCatalog2> pmsBaseCatalog2s = catelogService.getCatalog2(catalog1Id);
        return pmsBaseCatalog2s;
    }

    @PostMapping("getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        List<PmsBaseCatalog3> pmsBaseCatalog3s = catelogService.getCatalog3(catalog2Id);
        return pmsBaseCatalog3s;
    }

}
