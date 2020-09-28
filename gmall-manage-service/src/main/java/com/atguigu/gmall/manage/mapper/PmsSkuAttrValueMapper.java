package com.atguigu.gmall.manage.mapper;

import com.atguigu.gmall.bean.PmsSkuAttrValue;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuAttrValueMapper extends Mapper<PmsSkuAttrValue> {
    void insertBatch(@Param("skuAttrValueList") List<PmsSkuAttrValue> skuAttrValueList);
}
