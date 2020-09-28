package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.atguigu.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.atguigu.gmall.service.AttrInfoService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttrServiceImpl implements AttrInfoService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;


    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
        pmsBaseAttrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = pmsBaseAttrInfoMapper.select(pmsBaseAttrInfo);

        long start = System.currentTimeMillis();
        List<String> collect = pmsBaseAttrInfoList.stream().map(PmsBaseAttrInfo::getId).collect(Collectors.toList());
        List<PmsBaseAttrValue> pmsBaseAttrValues = pmsBaseAttrValueMapper.selectByAttrIdList(collect);

        for (int i = 0; i < pmsBaseAttrInfoList.size(); i++) {
            PmsBaseAttrInfo pmsBaseAttrInfo1 = pmsBaseAttrInfoList.get(i);
            List<PmsBaseAttrValue> list = pmsBaseAttrValues.stream().filter((item) -> item.getAttrId().equals(pmsBaseAttrInfo1.getId())).collect(Collectors.toList());
            pmsBaseAttrInfo1.setAttrValueList(list);
        }

        long end = System.currentTimeMillis();
        System.out.println("花费::================" + (end - start));
        return pmsBaseAttrInfoList;
    }

    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        String id = pmsBaseAttrInfo.getId();
        if (StringUtils.isEmpty(id)) {
            pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);

            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();

            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());

                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }else {
            Example example = new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
            pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

            List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();

            PmsBaseAttrValue pmsBaseAttrValueDel = new PmsBaseAttrValue();
            pmsBaseAttrValueDel.setAttrId(pmsBaseAttrInfo.getId());
            pmsBaseAttrValueMapper.delete(pmsBaseAttrValueDel);

            for (PmsBaseAttrValue pmsBaseAttrValue : attrValueList) {
                pmsBaseAttrValueMapper.insertSelective(pmsBaseAttrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
        pmsBaseAttrValue.setAttrId(attrId);
        return    pmsBaseAttrValueMapper.select(pmsBaseAttrValue);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {

        return pmsBaseSaleAttrMapper.selectAll();
    }
}
