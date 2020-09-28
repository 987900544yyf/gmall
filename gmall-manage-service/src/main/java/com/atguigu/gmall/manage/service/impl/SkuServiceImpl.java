package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsSkuAttrValue;
import com.atguigu.gmall.bean.PmsSkuImage;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import com.atguigu.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuImageMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.atguigu.gmall.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        pmsSkuInfoMapper.insertSelective(pmsSkuInfo);

        String skuId = pmsSkuInfo.getSpuId();

        //平台属性
        List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
        if (!skuAttrValueList.isEmpty()) {
            List<PmsSkuAttrValue> pmsSkuAttrValueList = new ArrayList<>();
            for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
                pmsSkuAttrValue.setSkuId(skuId);
                pmsSkuAttrValueList.add(pmsSkuAttrValue);
            }
            pmsSkuAttrValueMapper.insertBatch(skuAttrValueList);
        }


        //销售属性
        List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
        if (!skuSaleAttrValueList.isEmpty()) {
            List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList = new ArrayList<>();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                pmsSkuSaleAttrValue.setSkuId(skuId);
                pmsSkuSaleAttrValueList.add(pmsSkuSaleAttrValue);
            }
            pmsSkuSaleAttrValueMapper.insertBatch(pmsSkuSaleAttrValueList);
        }


        //图片信息
        List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
        if( !skuImageList.isEmpty()) {
            List<PmsSkuImage> pmsSkuImageList = new ArrayList<>();
            for (PmsSkuImage pmsSkuImage : skuImageList) {
                pmsSkuImage.setSkuId(skuId);
                pmsSkuImageList.add(pmsSkuImage);
            }
            pmsSkuImageMapper.insertBatch(pmsSkuImageList);
        }

    }
}
