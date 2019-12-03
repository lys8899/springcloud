package com.lys.usermanager.service;


import com.lys.usermanager.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

/**
 * @description: 用户
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:08
 **/
public interface UserService extends UserDetailsService {

    /**
     * 新增用户
     *
     * @param sysUser
     * @return 保存后的对象
     */
    SysUser addUser(SysUser sysUser);

    /**
     * 根据用户id删除用户
     *
     * @param userIds
     */
    void deleteUsers(Set<String> userIds);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Optional<SysUser> getUserById(String id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Optional<SysUser> getUserByIdAll(String id);

    /**
     * 根据姓名查询用户
     *
     * @param name     姓名
     * @param pageable 分页
     * @return UserDO 用户信息
     * @date 2017/11/30 15:5
     */
    Page<SysUser> listTable(String name, Pageable pageable);

    /**
     * 修改密码
     *
     * @param userId      用户id
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @param version     乐观锁
     * @return 1, 成功；2，用户不存在；3，原始密码错误
     */
    int changePassword(String userId, String oldPassword, String newPassword, Long version);


    void bindUserAndRoles(String userId, String[] roleIds);


}
