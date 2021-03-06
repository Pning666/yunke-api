package com.yunke.core.module.studio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunke.common.core.constant.SystemConstant;
import com.yunke.common.core.entity.QueryParam;
import com.yunke.common.core.entity.studio.Certificate;
import com.yunke.common.core.exception.ApiException;
import com.yunke.common.core.util.SortUtil;
import com.yunke.core.module.studio.mapper.CertificateMapper;
import com.yunke.core.module.studio.service.ICertificateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

/**
 * 资格证表(Certificate)表服务实现类
 *
 * @author chachae
 * @since 2020-06-14 14:04:56
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements
    ICertificateService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCertificate(Certificate certificate) {
            this.save(certificate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCertificate(Certificate certificate) {
        this.updateById(certificate);
    }

    @Override
    public IPage<Certificate> certificateList(QueryParam param, Certificate certificate) {
        Page<Certificate> page = new Page<>(param.getPageNum(), param.getPageSize());
        SortUtil.handlePageSort(param, page, "id", SystemConstant.ORDER_ASC, true);
        return baseMapper.pageCertificate(page,certificate);
    }

    @Override
    public Map<String,Object> getCertificate(String id) {
        return baseMapper.getCertificate(id);
    }

    @Override
    public void deleteCertificate(String[] ids) {this.removeByIds(Arrays.asList(ids)); }
}