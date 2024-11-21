package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentRequestFiles;

@Mapper
public interface AdjustmentRequestFilesMapper {
    List<AdjustmentRequestFiles> selectAll();
    AdjustmentRequestFiles selectByFileID(Integer fileID);
    int insert(AdjustmentRequestFiles adjustmentRequestFiles);
    int update(AdjustmentRequestFiles adjustmentRequestFiles);
    int deleteByFileID(Integer fileID);
    
    List<AdjustmentRequestFiles> selectByStatus(@Param("status") String status);
    
    int deleteByFileName(@Param("fileName") String fileName);

}
