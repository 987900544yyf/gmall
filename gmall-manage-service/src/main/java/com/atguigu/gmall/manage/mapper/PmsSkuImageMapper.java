package com.atguigu.gmall.manage.mapper;

import com.atguigu.gmall.bean.PmsSkuImage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsSkuImageMapper extends Mapper<PmsSkuImage> {
    void insertBatch(@Param("pmsSkuImageList") List<PmsSkuImage> pmsSkuImageList);
}
