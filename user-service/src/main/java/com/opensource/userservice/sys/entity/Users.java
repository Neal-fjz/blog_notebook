package com.opensource.userservice.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.opensource.userservice.common.group.GroupDelete;
import com.opensource.userservice.common.group.GroupUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 风君子
 * @since 2022-03-03 21:28:21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一 id 自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "修改时必须有 id", groups = {GroupDelete.class, GroupUpdate.class})
    private Integer id;

    /**
     * 用户登录名 唯一 长度限制 50
     */
    private String userName;

    /**
     * 用户密码 不能为空 需经过加密后存入 长度限制 500
     */
    private String pwd;

    /**
     * 手机号码 可空
     */
    private String phone;

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 创建时间 datetime
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 接受从前端传入的日期格式，映射到java类日期属性的规则
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 从数据库读出日期格式时，进行转换的规则
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 接受从前端传入的日期格式，映射到java类日期属性的规则
    private LocalDateTime updateTime;

    /**
     * 乐观锁版本号 默认为 0 每次修改增加 1
     */
    private Integer version;

    /**
     * 逻辑删除状态 默认 0 表示未删除，1 表示已删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 封禁状态 默认为 0 表示可用，1 表示被封禁
     */
    private Boolean locked;


    // 当前页码, 约定默认为 1
    @TableField(exist = false)
    private Integer current;

    // 每页数量 约定默认为 20
    @TableField(exist = false)
    private Integer pageSize;
}
