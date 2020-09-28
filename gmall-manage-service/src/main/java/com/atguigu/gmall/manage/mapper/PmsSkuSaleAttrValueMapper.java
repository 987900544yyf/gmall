package com.atguigu.gmall.manage.mapper;

import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuSaleAttrValueMapper extends Mapper<PmsSkuSaleAttrValue> {
    void insertBatch(@Param("pmsSkuSaleAttrValueList") List<PmsSkuSaleAttrValue> pmsSkuSaleAttrValueList);
}
