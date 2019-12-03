package com.lys.usermanager.repository;


import com.lys.usermanager.entity.SysRowFunction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * @description: 用户角色中间表
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface RowFunctionRepository extends JpaRepository<SysRowFunction, String> {

    /**
     * 根据用户id查询中间表
     *
     * @param stringSet 角色id
     * @return
     */
    List<SysRowFunction> findByfRoleIdIn(Set<String> stringSet);
}
