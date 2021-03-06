package com.yunke.core.module.studio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yunke.common.core.entity.studio.SchoolAssets;
import com.yunke.common.core.entity.studio.SchoolAssetsRepair;
import org.apache.ibatis.annotations.Param;

/**
 * 维修信息表
(SchoolAssetsRepair)表数据库访问层
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface SchoolAssetsRepairMapper extends BaseMapper<SchoolAssetsRepair> {
    /**
     * 查找资产维修申请的详细信息
     * @param page 分页对象
     * @param <T>  type
     * @param statrtTime 开始时间
     * @param endTime     结束时间
     * @return <T> IPage<SchoolAssetsRepair>
     */
    <T> IPage<SchoolAssetsRepair> pageschoolAssetsRepairDetail(Page<T> page, @Param("schoolAssetsRepair") SchoolAssetsRepair schoolAssetsRepair,@Param("statrtTime") String statrtTime,@Param("endTime")String endTime);

    /**
     * 根据提供的id删除资产维修申请数据
     * @param schoolAssetsRepairIds 学校资产id数组
     */
    void deleteSchoolAssetsRepairById(int[] schoolAssetsRepairIds);

    /**
     * 添加资产维修申请
     * @param schoolAssetsRepair 资产维修申请对象
     */
    void addSchoolAssetsRepair(@Param("schoolAssetsRepair")SchoolAssetsRepair schoolAssetsRepair);

    /**
     * 根据提供的id修改学校资产维修申请数据
     * @param schoolAssetsRepair 维修申请对象
     */
    void updateSchoolAssetsRepairsMessage(@Param("schoolAssetsRepair")SchoolAssetsRepair schoolAssetsRepair);

    /**
     * 通过经费id返回该维修申请数据
     * @param schoolAssetsRepairId 维修申请id
     */
    SchoolAssetsRepair selectSchoolAssetsRepairIdById(@Param("schoolAssetsRepairId")int schoolAssetsRepairId);

    /**
     * 根据提供的id修改学校资产维修状态
     * @param schoolAssetsRepair 维修申请对象
     */
    void updateSchoolAssetsRepairsState(@Param("schoolAssetsRepair")SchoolAssetsRepair schoolAssetsRepair);
}