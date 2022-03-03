package com.opensource.userservice.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opensource.userservice.common.exception.FailException;
import com.opensource.userservice.common.group.GroupDelete;
import com.opensource.userservice.common.group.GroupFind;
import com.opensource.userservice.common.group.GroupInsert;
import com.opensource.userservice.common.group.GroupUpdate;
import com.opensource.userservice.common.resultful.CodeEnum;
import com.opensource.userservice.sys.entity.Users;
import com.opensource.userservice.sys.service.UsersService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 风君子
 * @description users前端控制器  RestController注解
 * @DateTime 2022-03-03 21:28:21
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService usersService;

    /**
     * 1.0 mybatis-plus Generator自动生成 保存--单条创建
     * 请求方式: POST
     *
     * @param users 接受对象
     * @return CodeEnum 返回枚举
     */
    @PostMapping("/save")
    public CodeEnum save(@RequestBody @Validated({GroupInsert.class}) Users users) {
        boolean success = usersService.save(users);
        if (success) {
            return CodeEnum.SAVE_SUCCESS;
        }
        throw new FailException(CodeEnum.SAVE_FAIL);
    }

    /**
     * 2.0 mybatis-plus Generator自动生成  删除--单条逻辑删除
     * 请求方式: POST
     *
     * @return CodeEnum 返回枚举
     */
    @PostMapping("delete_one")
    public CodeEnum deleteOne(@RequestBody @Validated({GroupDelete.class}) Users users) {
        boolean success = usersService.removeById(users.getId());
        if (success) {
            return CodeEnum.DELETE_ONE_SUCCESS;
        }
        throw new FailException(CodeEnum.DELETE_ONE_FAIL);
    }

    /**
     * 3.0 mybatis-plus Generator自动生成 删除--批量逻辑删除
     * 请求方式: POST
     *
     * @param ids Long 类型 List 集合
     * @return CodeEnum 返回枚举
     */
    @PostMapping("remove_batch")
    public CodeEnum deleteBatch(@RequestBody List<Integer> ids) {
        if (ids != null && ids.isEmpty()) {
            boolean success = usersService.removeByIds(ids);
            if (success) {
                return CodeEnum.DELETE_LOT_SUCCESS;
            }
            throw new FailException(CodeEnum.DELETE_LOT_FAIL);
        }
        throw new FailException(CodeEnum.NON_MEMBER);
    }

    /**
     * 4.0 mybatis-plus Generator自动生成 更新--单条修改
     * 请求方式: POST
     *
     * @param users 修改对象
     * @return CodeEnum 返回枚举
     */
    @PostMapping("/update/one")
    public CodeEnum update(@RequestBody @Validated({GroupUpdate.class}) Users users) {
        boolean flag = usersService.updateById(users);
        if (flag) {
            return CodeEnum.UPDATE_SUCCESS;
        }
        throw new FailException(CodeEnum.UPDATE_FAIL);
    }

    /**
     * 5.0. mybatis-plus Generator自动生成 查询--单条
     * 请求方式: GET
     * @param users 查询对象
     * @return Users 返回对象
     */
    @GetMapping("query_one")
    public Users queryOne(@ModelAttribute @Validated({GroupFind.class}) Users users) {
        return usersService.getById(users.getId());
    }

    /**
     * 6.0 mybatis-plus Generator自动生成 查询--根据条件查询列表 分页查询
     * 请求方式: GET
     * @param users 查询对象
     * @return page 返回列表
     */
    @GetMapping("query_by_condition")
    public Page<Users> queryByCondition(@ModelAttribute @Validated({GroupFind.class}) Users users) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(StrUtil.isNotBlank(users.getUserName()), Users::getUserName, users.getUserName());

        Page<Users> page = new Page<>(users.getCurrent(), users.getPageSize());
        usersService.page(page, queryWrapper);
        return page;
    }
}
