package com.atguigu.gmall.manage.mapper;

import com.atguigu.gmall.bean.PmsBaseAttrValue;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PmsBaseAttrValueMapper extends Mapper<PmsBaseAttrValue> {
    List<PmsBaseAttrValue> selectByAttrIdList(@Param("list") List<String> list);
}
