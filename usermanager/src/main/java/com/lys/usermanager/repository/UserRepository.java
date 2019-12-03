package com.lys.usermanager.repository;

import com.lys.usermanager.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @description: 用户Repository
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface UserRepository extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {


    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return UserDO 用户信息
     * @date 2017/11/30 15:5
     */
    Optional<SysUser> findByfAccount(String account);


    /**
     * 根据id和版本号查询用户
     *
     * @param id      id
     * @param version 版本号
     * @return
     */
    Optional<SysUser> findByfIdAndVersion(String id, Long version);

}
