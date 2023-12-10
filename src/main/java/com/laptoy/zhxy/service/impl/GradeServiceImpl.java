package com.laptoy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laptoy.zhxy.pojo.Grade;
import com.laptoy.zhxy.service.GradeService;
import com.laptoy.zhxy.mapper.GradeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author lapto
 * @description 针对表【tb_grade】的数据库操作Service实现
 * @createDate 2022-04-13 19:46:33
 */
@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Override
    public IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName) {

        QueryWrapper<Grade> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(gradeName)) {
            wrapper.like("name", gradeName);
        }
        wrapper.orderByAsc("id");

        Page<Grade> gradePage = baseMapper.selectPage(page, wrapper);

        return gradePage;
    }

    @Override
    public List<Grade> getGrades() {
        List<Grade> grades = baseMapper.selectList(null);
        return grades;
    }
}




