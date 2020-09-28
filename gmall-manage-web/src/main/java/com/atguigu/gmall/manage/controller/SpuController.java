package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsProductImage;
import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.service.SpuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.fdfs.UploadUtil;

import java.util.List;

@RestController
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;


    @GetMapping("spuList")
    public List<PmsProductInfo> spuList(String catalog3Id) {
        return spuService.spuList(catalog3Id);
    }

    @GetMapping("spuImageList")
    public List<PmsProductImage> spuImageList(String spuId) {
        return spuService.spuImageList(spuId);
    }

    @GetMapping("spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        return  spuService.spuSaleAttrList(spuId);
    }


    @RequestMapping("saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }


    @PostMapping("fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
    //将图片发送到fdfs
        String url = UploadUtil.uploadImage(file);
        System.out.println(url);
        //     返回路径
        return url;
    }


}
