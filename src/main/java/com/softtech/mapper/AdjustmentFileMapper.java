package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentFile;

@Mapper
public interface AdjustmentFileMapper {
    List<AdjustmentFile> selectAll();
    AdjustmentFile selectByFileID(Integer fileID);
    int insert(AdjustmentFile adjustmentFile);
    int update(AdjustmentFile adjustmentFile);
    int deleteByFileID(Integer fileID);
    List<AdjustmentFile> findFilesByEmployeeEmail(String employeeEmail); 
    
    AdjustmentFile findByEmployeeIDAndYearAndFileName(@Param("employeeID") String employeeID, @Param("fileYear") int fileYear, @Param("fileName") String fileName);

    int updateByEmployeeIDAndYearAndFileName(AdjustmentFile adjustmentFile);

}
