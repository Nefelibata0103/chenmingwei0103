package com.laptoy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laptoy.zhxy.pojo.LoginForm;
import com.laptoy.zhxy.pojo.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lapto
* @description 针对表【tb_teacher】的数据库操作Service
* @createDate 2022-04-13 19:46:33
*/
public interface TeacherService extends IService<Teacher> {

    Teacher login(LoginForm loginForm);

    Teacher getTeacherById(int intValue);

    IPage<Teacher> getTeachersByOpr(Page<Teacher> pageParam, Teacher teacher);
}
