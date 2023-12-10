package com.laptoy.zhxy.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laptoy.zhxy.pojo.Admin;
import com.laptoy.zhxy.pojo.Student;
import com.laptoy.zhxy.pojo.Teacher;
import com.laptoy.zhxy.service.AdminService;
import com.laptoy.zhxy.service.StudentService;
import com.laptoy.zhxy.service.TeacherService;
import com.laptoy.zhxy.util.JwtHelper;
import com.laptoy.zhxy.util.MD5;
import com.laptoy.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "系统管理员控制器")
@RestController
@RequestMapping("/sms/adminController")
public class AdminController {


    @Autowired
    private AdminService adService;

    @ApiOperation("分页获取所有Admin信息【带条件】")
    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(@PathVariable Integer pageNo,
                              @PathVariable Integer pageSize,
                              String adminName) {
        Page<Admin> pageParam = new Page<>(pageNo, pageSize);
        IPage<Admin> page = adService.getAdmins(pageParam, adminName);
        return Result.ok(page);
    }

    @ApiOperation("添加或修改Admin信息")
    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@RequestBody Admin admin) {
        if (!Strings.isEmpty(admin.getPassword())) {
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        }
        adService.saveOrUpdate(admin);
        return Result.ok();
    }

    @ApiOperation("删除Admin信息")
    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(@RequestBody List<Integer> ids) {
        adService.removeByIds(ids);
        return Result.ok();
    }


}