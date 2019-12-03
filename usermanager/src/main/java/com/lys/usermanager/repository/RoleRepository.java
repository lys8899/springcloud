package com.lys.usermanager.repository;

import com.lys.usermanager.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @description: 角色Repository
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface RoleRepository extends JpaRepository<SysRole, String> {

}
