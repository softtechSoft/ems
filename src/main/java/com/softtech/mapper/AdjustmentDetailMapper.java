package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentDetail;

@Mapper
public interface AdjustmentDetailMapper {
    List<AdjustmentDetail> selectAll();
    AdjustmentDetail selectById(Integer id);
    int insert(AdjustmentDetail adjustmentDetail);
    int update(AdjustmentDetail adjustmentDetail);
    int deleteById(Integer id);
    List<AdjustmentDetail> findByEmployeeEmailAndYear(@Param("employeeEmail") String employeeEmail, @Param("year") int year);
    AdjustmentDetail findByEmployeeIdAndYear(@Param("employeeID") String employeeID, @Param("year") String year);


}
