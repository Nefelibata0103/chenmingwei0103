package com.laptoy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laptoy.zhxy.pojo.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author lapto
* @description 针对表【tb_grade】的数据库操作Service
* @createDate 2022-04-13 19:46:33
*/
public interface GradeService extends IService<Grade> {

    IPage<Grade> getGradeByOpr(Page<Grade> page, String gradeName);

    List<Grade> getGrades();
}
