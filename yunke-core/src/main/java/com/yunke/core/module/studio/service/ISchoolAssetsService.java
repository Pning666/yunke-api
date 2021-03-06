package com.yunke.core.module.studio.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Funding;
import com.yunke.common.core.entity.studio.SchoolAssets;
import org.apache.ibatis.annotations.Param;

/**
 * 学校资产表
(SchoolAssets)表服务接口
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
public interface ISchoolAssetsService extends IService<SchoolAssets> {
    /**
     * 分页查询学校资产数据
     * @param schoolAssets 查询学校资产对象
     * @param param 分页参数
     * @return 分页对象
     */
    IPage<SchoolAssets> pageSchoolAssets(QueryParam param, SchoolAssets schoolAssets);

    /**
     * 通过id查询该资产数据
     * @param id 资产id
     * @return SchoolAssets
     */
    SchoolAssets selectSchoolAssetsById(int id);

    /**
     * 添加学校资产
     * @param schoolAssets 学校资产
     */
    void addSchoolAssets(SchoolAssets schoolAssets);

    /**
     * 根据提供的id删除学校资产数据
     * @param schoolAssetsIds 学校资产id数组
     */
    void deleteSchoolAssetsById(int[] schoolAssetsIds);

    /**
     * 根据提供的id修改学校资产数据
     * @param schoolAssets 学校资产
     */
    void updateSchoolAssetsMessage(SchoolAssets schoolAssets);
}