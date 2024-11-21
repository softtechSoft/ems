package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.AdjustmentFile;

@Mapper
public interface AdjustmentFileMapper {
    List<AdjustmentFile> selectAll();
    AdjustmentFile selectByFileID(Integer fileID);
    int insert(AdjustmentFile adjustmentFile);
    int update(AdjustmentFile adjustmentFile);
    int deleteByFileID(Integer fileID);
    List<AdjustmentFile> findFilesByEmployeeEmail(String employeeEmail); 
}
