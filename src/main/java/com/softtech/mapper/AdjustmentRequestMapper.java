package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.AdjustmentRequest;

@Mapper
public interface AdjustmentRequestMapper {
    List<AdjustmentRequest> selectAll();
    AdjustmentRequest selectById(Integer id);
    int insert(AdjustmentRequest adjustmentRequest);
    int update(AdjustmentRequest adjustmentRequest);
    int deleteById(Integer id);
}
