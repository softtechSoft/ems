package com.softtech.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentDetail;

/**
 * 調整詳細情報を操作するためのマッパーインターフェース
 * 
 * このインターフェースは、MyBatisを使用してデータベースとやり取りを行い、
 * 調整詳細情報（AdjustmentDetail）のCRUD操作を提供します。
 */
@Mapper
public interface AdjustmentDetailMapper {
    
    /**
     * すべての調整詳細情報を取得します。
     * 
     * @return 調整詳細情報のリスト
     */
    List<AdjustmentDetail> selectAll();
    
    /**
     * 指定されたIDに基づいて調整詳細情報を取得します。
     * 
     * @param id 調整詳細情報のID
     * @return 指定されたIDに対応する調整詳細情報
     */
    AdjustmentDetail selectById(Integer id);
    
    /**
     * 新しい調整詳細情報をデータベースに挿入します。
     * 
     * @param adjustmentDetail 挿入する調整詳細情報のオブジェクト
     * @return 挿入に成功した場合は1、失敗した場合は0
     */
    int insert(AdjustmentDetail adjustmentDetail);
    
    /**
     * 既存の調整詳細情報を更新します。
     * 
     * @param adjustmentDetail 更新する調整詳細情報のオブジェクト
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    int update(AdjustmentDetail adjustmentDetail);
    
    /**
     * 指定されたIDに基づいて調整詳細情報を削除します。
     * 
     * @param id 削除する調整詳細情報のID
     * @return 削除に成功した場合は1、失敗した場合は0
     */
    int deleteById(Integer id);
    
    /**
     * 従業員IDと年度に基づいて調整詳細情報を取得します。
     * 
     * @param employeeID 従業員のID
     * @param year       調整対象の年度
     * @return 指定された従業員IDと年度に対応する調整詳細情報
     */
    AdjustmentDetail findByEmployeeIdAndYear(@Param("employeeID") String employeeID, @Param("year") String year);
    
    /**
     * 従業員IDと年度に基づいてアップロードステータスを更新します。
     * 
     * @param employeeID   従業員のID
     * @param year         調整対象の年度
     * @param uploadStatus 更新するアップロードステータス（例: "1" = 完了, "0" = 未完了）
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    int updateUploadStatusByEmployeeIdAndYear(@Param("employeeID") String employeeID, @Param("year") String year, @Param("uploadStatus") String uploadStatus);

}
