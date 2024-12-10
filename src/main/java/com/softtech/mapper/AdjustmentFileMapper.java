package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentFile;

/**
 * 調整ファイル情報を操作するためのマッパーインターフェース
 * 
 * このインターフェースは、MyBatisを使用してデータベースとやり取りを行い、
 * 調整ファイル情報（AdjustmentFile）のCRUD操作および特定のクエリを提供します。
 */
@Mapper
public interface AdjustmentFileMapper {
    
    /**
     * すべての調整ファイル情報を取得します。
     * 
     * @return 調整ファイル情報のリスト
     */
    List<AdjustmentFile> selectAll();
    
    /**
     * 新しい調整ファイル情報をデータベースに挿入します。
     * 
     * @param adjustmentFile 挿入する調整ファイル情報のオブジェクト
     * @return 挿入に成功した場合は1、失敗した場合は0
     */
    int insert(AdjustmentFile adjustmentFile);
    
    /**
     * 既存の調整ファイル情報を更新します。
     * 
     * @param adjustmentFile 更新する調整ファイル情報のオブジェクト
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    int update(AdjustmentFile adjustmentFile);
    
    /**
     * 指定された従業員IDに関連する調整ファイルを取得します。
     * 
     * @param employeeID 従業員のID
     * @return 指定された従業員IDに対応する調整ファイルのリスト
     */
    List<AdjustmentFile> findFilesByEmployeeID(String employeeID); 
    
    /**
     * 従業員ID、年度、ファイル名、およびファイルタイプに基づいて調整ファイルを取得します。
     * 
     * @param employeeID 従業員のID
     * @param fileYear   ファイルの年度
     * @param fileName   ファイル名
     * @param fileType   ファイルのタイプ（例: "resultType", "detailType"）
     * @return 指定された条件に一致する調整ファイル情報
     */
    AdjustmentFile findByEmployeeIDAndYearAndFileName(
        @Param("employeeID") String employeeID,
        @Param("fileYear") int fileYear,
        @Param("fileName") String fileName,
        @Param("fileType") String fileType
    );
    
    /**
     * 指定された従業員ID、年度、およびファイル名に基づいて調整ファイル情報を更新します。
     * 
     * @param adjustmentFile 更新する調整ファイル情報のオブジェクト
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    int updateByEmployeeIDAndYearAndFileName(AdjustmentFile adjustmentFile);
    
    /**
     * 指定されたファイルタイプ、従業員ID、および年度に基づいて調整ファイルを取得します。
     * 
     * @param fileType   ファイルの種類（例: "resultType", "detailType"）
     * @param employeeID 従業員のID
     * @param fileYear   ファイルの年度
     * @return 指定された条件に一致する調整ファイルのリスト
     */
    List<AdjustmentFile> findFilesByTypeAndEmployee(
        @Param("fileType") String fileType,
        @Param("employeeID") String employeeID,
        @Param("fileYear") int fileYear
    );
    
    /**
     * 指定された従業員IDと年度に基づいてファイルステータスを更新します。
     * 
     * @param params 更新に必要なパラメータを含むマップ
     *               - "employeeID": 従業員のID
     *               - "year": ファイルの年度
     *               - "fileStatus": 更新するファイルステータス（例: "1" = 確定, "0" = 未確定）
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    void updateFileStatusByEmployeeIDAndYear(Map<String, Object> params);
    
    /**
     * 指定された従業員IDに関連する結果ファイルを取得します。
     * 
     * @param employeeID 従業員のID
     * @return 指定された従業員IDに対応する結果ファイルのリスト
     */
    List<AdjustmentFile> findResultFilesByEmployeeID(@Param("employeeID") String employeeID);
}
