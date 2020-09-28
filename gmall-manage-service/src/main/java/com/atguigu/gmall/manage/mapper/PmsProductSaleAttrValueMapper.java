package com.atguigu.gmall.manage.mapper;

import com.atguigu.gmall.bean.PmsProductSaleAttrValue;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsProductSaleAttrValueMapper extends Mapper<PmsProductSaleAttrValue>{
    List<PmsProductSaleAttrValue> selectByAttrIdList(@Param("productId") String supId, @Param("saleAttrIdCollects") List<String> saleAttrIdCollects);
}
