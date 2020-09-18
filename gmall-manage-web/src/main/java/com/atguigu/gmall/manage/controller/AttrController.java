package com.atguigu.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.service.AttrInfoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AttrController {

    @Reference
    AttrInfoService attrInfoService;


    @GetMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
    List<PmsBaseAttrInfo> pmsBaseAttrInfoList = attrInfoService.attrInfoList(catalog3Id);
        return pmsBaseAttrInfoList;
}

    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo) {
        String falg = attrInfoService.saveAttrInfo(pmsBaseAttrInfo);
        return "suucess";
    }

    @RequestMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
       return attrInfoService.getAttrValueList(attrId);

    }
}
