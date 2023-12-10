package com.laptoy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laptoy.zhxy.pojo.Admin;
import com.laptoy.zhxy.pojo.LoginForm;
import com.laptoy.zhxy.pojo.Student;
import com.laptoy.zhxy.service.StudentService;
import com.laptoy.zhxy.mapper.StudentMapper;
import com.laptoy.zhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lapto
 * @description 针对表【tb_student】的数据库操作Service实现
 * @createDate 2022-04-13 19:46:33
 */
@Service
@Transactional
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    /**
     * 学生登录方法
     */
    @Override
    public Student login(LoginForm loginForm) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("name", loginForm.getUsername());
        wrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        return baseMapper.selectOne(wrapper);

    }

    @Override
    public Student getStudentById(int intValue) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("id", intValue);
        Student student = baseMapper.selectOne(wrapper);
        return student;
    }

    /**
     * 按条件查询学生信息【带分页】
     */
    public IPage<Student> getStudentByOpr(Page<Student> pageParam, Student student) {
        QueryWrapper<Student> queryWrapper = null;
        if (student != null) {
            queryWrapper = new QueryWrapper<>();
            if (student.getClazzName() != null) {
                queryWrapper.eq("clazz_name", student.getClazzName());
            }
            if (student.getName() != null) {
                queryWrapper.like("name", student.getName());
            }
            queryWrapper.orderByAsc("id");
        }
        //创建分页对象
        IPage<Student> pages = baseMapper.selectPage(pageParam, queryWrapper);

        return pages;
    }
}




