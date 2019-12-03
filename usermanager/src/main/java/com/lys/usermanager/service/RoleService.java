package com.lys.usermanager.service;

import com.lys.usermanager.entity.SysRole;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @description: 角色
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-09 13:39
 **/
public interface RoleService {
    /**
     * 保存
     *
     * @param sysRole
     * @return sysRole
     */
    SysRole saveRole(SysRole sysRole);

    /**
     * 根据用户id查询角色
     *
     * @param userId
     * @return 角色
     */
    List<SysRole> findRolesByUserId(String userId);

    /**
     * 根据用户id删除角色
     *
     * @param roleIds
     */
    void deleteRoles(Set<String> roleIds);

    /**
     * 根据用户id查询角色
     *
     * @param id
     * @return 角色
     */
    Optional<SysRole> findById(String id);


}
