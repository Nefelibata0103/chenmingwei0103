package com.laptoy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laptoy.zhxy.pojo.Clazz;
import com.laptoy.zhxy.service.ClazzService;
import com.laptoy.zhxy.mapper.ClazzMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lapto
 * @description 针对表【tb_clazz】的数据库操作Service实现
 * @createDate 2022-04-13 19:46:33
 */
@Service
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    /**
     * 分页查询所有班级信息【带条件】
     *
     * @param clazz
     * @return
     */
    @Override
    public IPage<Clazz> getClazzsByOpr(Page<Clazz> pageParam, Clazz clazz) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        if (clazz != null) {
            //年级名称条件
            String gradeName = clazz.getGradeName();
            if (!StringUtils.isEmpty(gradeName)) {
                queryWrapper.eq("grade_name", gradeName);
            }
            //班级名称条件
            String clazzName = clazz.getName();
            if (!StringUtils.isEmpty(clazzName)) {
                queryWrapper.like("name", clazzName);
            }
            queryWrapper.orderByAsc("id");
        }
        Page<Clazz> clazzPage = baseMapper.selectPage(pageParam, queryWrapper);
        return clazzPage;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}




