package com.softtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.AdjustmentRequestFiles;

/**
 * 調整申請ファイル情報を操作するためのマッパーインターフェース
 * 
 * このインターフェースは、MyBatisを使用してデータベースとやり取りを行い、
 * 調整申請ファイル情報（AdjustmentRequestFiles）のCRUD操作および特定のクエリを提供します。
 */
@Mapper
public interface AdjustmentRequestFilesMapper {
    
    /**
     * すべての調整申請ファイル情報を取得します。
     * 
     * @return 調整申請ファイル情報のリスト
     */
    List<AdjustmentRequestFiles> selectAll();

    /**
     * 新しい調整申請ファイル情報をデータベースに挿入します。
     * 
     * @param adjustmentRequestFiles 挿入する調整申請ファイル情報のオブジェクト
     * @return 挿入に成功した場合は1、失敗した場合は0
     */
    int insert(AdjustmentRequestFiles adjustmentRequestFiles);
    
    /**
     * 既存の調整申請ファイル情報を更新します。
     * 
     * @param adjustmentRequestFiles 更新する調整申請ファイル情報のオブジェクト
     * @return 更新に成功した場合は1、失敗した場合は0
     */
    int update(AdjustmentRequestFiles adjustmentRequestFiles);

    /**
     * 指定されたステータスに基づいて調整申請ファイル情報を取得します。
     * 
     * @param status 取得するファイルのステータス（例: "1" = 有効, "0" = 無効）
     * @return 指定されたステータスに対応する調整申請ファイル情報のリスト
     */
    List<AdjustmentRequestFiles> selectByStatus(@Param("status") String status);
    
    /**
     * 指定されたファイル名に基づいて調整申請ファイル情報を削除します。
     * 
     * @param fileName 削除するファイルの名前
     * @return 削除に成功した場合は1、失敗した場合は0
     */
    int deleteByFileName(@Param("fileName") String fileName);
    
    /**
     * 指定されたステータスに基づいて調整申請ファイル情報をマップ形式で取得します。
     * 
     * @param status 取得するファイルのステータス（例: "1" = 有効, "0" = 無効）
     * @return 調整申請ファイル情報をマップ形式で格納したリスト
     */
    List<Map<String, Object>> selectByStatusMapped(@Param("status") String status);
    
    /**
     * 指定された年度とステータスに基づいて調整申請ファイル情報を取得します。
     * 
     * @param year    取得するファイルの年度
     * @param status  取得するファイルのステータス（例: "1" = 有効, "0" = 無効）
     * @return 指定された年度とステータスに対応する調整申請ファイル情報のリスト
     */
    List<AdjustmentRequestFiles> findByYearAndStatus(@Param("year") int year, @Param("status") String status);
    
    /**
     * 指定されたファイル名、従業員ID、および年度に基づいて調整申請ファイル情報を取得します。
     * 
     * @param fileName   取得するファイルの名前
     * @param employeeID 従業員のID
     * @param fileYear   ファイルの年度
     * @return 指定された条件に一致する調整申請ファイル情報のリスト
     */
    List<AdjustmentRequestFiles> selectByFileNameAndEmployeeIDAndYear(
        @Param("fileName") String fileName,
        @Param("employeeID") String employeeID,
        @Param("fileYear") int fileYear
    );

    /**
     * 指定されたファイル名、従業員ID、および年度に基づいて単一の調整申請ファイル情報を取得します。
     * 
     * @param fileName   取得するファイルの名前
     * @param employeeID 従業員のID
     * @param fileYear   ファイルの年度
     * @return 指定された条件に一致する単一の調整申請ファイル情報
     */
    AdjustmentRequestFiles selectByFileNameAndEmployeeIDAndYearSingle(
        @Param("fileName") String fileName,
        @Param("employeeID") String employeeID,
        @Param("fileYear") int fileYear
    );

}
