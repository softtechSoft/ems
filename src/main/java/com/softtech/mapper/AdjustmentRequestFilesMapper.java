package com.softtech.mapper;

import java.util.List;
import java.util.Map;

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
    
    List<Map<String, Object>> selectByStatusMapped(@Param("status") String status);
    
    List<AdjustmentRequestFiles> findByYearAndStatus(@Param("year") int year, @Param("status") String status);
    
    List<AdjustmentRequestFiles> selectByFileNameAndEmployeeIDAndYear(@Param("fileName") String fileName, @Param("employeeID") String employeeID, @Param("fileYear") int fileYear);

    AdjustmentRequestFiles selectByFileNameAndEmployeeIDAndYearSingle(@Param("fileName") String fileName, @Param("employeeID") String employeeID, @Param("fileYear") int fileYear);

    int updateByEmployeeIDAndFileYearAndFileName(AdjustmentRequestFiles adjustmentRequestFiles);

    
}
