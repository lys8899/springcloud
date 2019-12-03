package com.lys.usermanager.service;

import com.lys.usermanager.entity.SysFunction;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @description: 权限
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-09 13:39
 **/
public interface FunctionService {

    /**
     * 保存
     *
     * @param function
     * @return SysFunction
     */
    SysFunction saveFunction(SysFunction function);

    /**
     * 根据角色id查询权限
     *
     * @param stringSet
     * @return List<SysFunction>
     */
    List<SysFunction> findFunctionsByRoleIds(Set<String> stringSet);

    /**
     * 根据id查询
     *
     * @param id
     * @return Optional
     */
    Optional<SysFunction> findById(String id);
}
