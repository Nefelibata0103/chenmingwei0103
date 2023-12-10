package com.laptoy.zhxy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laptoy.zhxy.pojo.Admin;
import com.laptoy.zhxy.pojo.LoginForm;
import com.laptoy.zhxy.service.AdminService;
import com.laptoy.zhxy.mapper.AdminMapper;
import com.laptoy.zhxy.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author lapto
 * @description 针对表【tb_admin】的数据库操作Service实现
 * @createDate 2022-04-13 19:46:33
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    /**
     * 超级管理员登录
     */
    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("name", loginForm.getUsername());
        // 转换为密文进行查询
        wrapper.eq("password", MD5.encrypt(loginForm.getPassword()));

        Admin admin = baseMapper.selectOne(wrapper);

        return admin;
    }

    @Override
    public Admin getAdminById(int intValue) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("id", intValue);
        Admin admin = baseMapper.selectOne(wrapper);
        return admin;
    }

    @Override
    public IPage<Admin> getAdmins(Page<Admin> pageParam, String adminName) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(adminName)) {
            queryWrapper.like("name", adminName);
        }

        queryWrapper.orderByAsc("id");
        Page page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }
}




