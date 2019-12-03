package com.lys.usermanager.repository;


import com.lys.usermanager.entity.SysUserRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description: 用户角色中间表
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface UserRowRepository extends JpaRepository<SysUserRow, String> {

    /**
     * 根据用户id查询中间表
     *
     * @param userId 用户id
     * @return
     */
    List<SysUserRow> findByfUserId(String userId);
}
