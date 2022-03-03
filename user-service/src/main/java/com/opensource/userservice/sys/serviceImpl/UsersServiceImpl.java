package com.opensource.userservice.sys.serviceImpl;

import com.opensource.userservice.sys.entity.Users;
import com.opensource.userservice.sys.mapper.UsersMapper;
import com.opensource.userservice.sys.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 风君子
 * @since 2022-03-03 21:28:21
 */
@Transactional(propagation = Propagation.REQUIRED)
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
