package com.laptoy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laptoy.zhxy.pojo.LoginForm;
import com.laptoy.zhxy.pojo.Student;
import com.laptoy.zhxy.pojo.Teacher;
import com.laptoy.zhxy.service.TeacherService;
import com.laptoy.zhxy.mapper.TeacherMapper;
import com.laptoy.zhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author lapto
 * @description 针对表【tb_teacher】的数据库操作Service实现
 * @createDate 2022-04-13 19:46:33
 */
@Service
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    /**
     * Teacher登录方法
     */
    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("name", loginForm.getUsername());
        wrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        return baseMapper.selectOne(wrapper);
    }

    @Override
    public Teacher getTeacherById(int intValue) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("id", intValue);
        Teacher teacher = baseMapper.selectOne(wrapper);
        return teacher;
    }

    @Override
    public IPage<Teacher> getTeachersByOpr(Page<Teacher> pageParam, Teacher teacher) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if (teacher != null) {
            //班级名称条件
            String clazzName = teacher.getClazzName();
            if (!StringUtils.isEmpty(clazzName)) {
                wrapper.eq("clazz_name", clazzName);
            }
            //教师名称条件
            String teacherName = teacher.getName();
            if (!StringUtils.isEmpty(teacherName)) {
                wrapper.like("name", teacherName);
            }
            wrapper.orderByAsc("id");
        }

        Page<Teacher> page = baseMapper.selectPage(pageParam, wrapper);

        return page;

    }
}




