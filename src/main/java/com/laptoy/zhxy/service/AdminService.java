package com.laptoy.zhxy.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laptoy.zhxy.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.laptoy.zhxy.pojo.LoginForm;

/**
* @author lapto
* @description 针对表【tb_admin】的数据库操作Service
* @createDate 2022-04-13 19:46:33
*/
public interface AdminService extends IService<Admin> {

    Admin login(LoginForm loginForm);

    Admin getAdminById(int intValue);

    IPage<Admin> getAdmins(Page<Admin> pageParam, String adminName);
}
