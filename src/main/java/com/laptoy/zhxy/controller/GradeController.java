package com.laptoy.zhxy.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laptoy.zhxy.pojo.Grade;
import com.laptoy.zhxy.service.GradeService;
import com.laptoy.zhxy.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "年级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 分页展示数据
     */
    @ApiOperation("查询年级信息,分页带条件")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGradeByOpr(@ApiParam("分页查询页码数") @PathVariable(value = "pageNo") Integer pageNo, // 页码数
                                @ApiParam("分页查询页大小") @PathVariable(value = "pageSize") Integer pageSize, // 页大小
                                @ApiParam("分页查询模糊匹配班级名") String gradeName) {
        // 设置分页信息
        Page<Grade> page = new Page<>(pageNo, pageSize);
        // 调用服务层方法,传入分页信息,和查询的条件
        IPage<Grade> pageRs = gradeService.getGradeByOpr(page, gradeName);
        return Result.ok(pageRs);
    }

    /**
     * 保存或更新信息
     */
    @ApiOperation("添加或者修改年级信息")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("JSON的grade对象转换后台数据模型") @RequestBody Grade grade) {

        // 调用服务层方法,实现添加或者修改年级信息
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    /**
     * 删除或批量删除
     */
    @ApiOperation("删除一个或者多个grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGradeById(@ApiParam("JSON的年级id集合,映射为后台List<Integer>") @RequestBody List<Integer> ids) {
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 回显搜索条件中的年级选项
     */
    @ApiOperation("获取所有Grade信息")
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }

}
