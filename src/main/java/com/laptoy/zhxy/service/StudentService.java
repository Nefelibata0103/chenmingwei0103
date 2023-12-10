package com.laptoy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laptoy.zhxy.pojo.LoginForm;
import com.laptoy.zhxy.pojo.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lapto
* @description 针对表【tb_student】的数据库操作Service
* @createDate 2022-04-13 19:46:33
*/
public interface StudentService extends IService<Student> {

    Student login(LoginForm loginForm);

    Student getStudentById(int intValue);

    IPage<Student> getStudentByOpr(Page<Student> page, Student student);
}
