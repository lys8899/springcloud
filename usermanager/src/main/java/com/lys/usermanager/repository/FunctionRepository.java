package com.lys.usermanager.repository;

import com.lys.usermanager.entity.SysFunction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description: 权限Repository
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:00
 **/
public interface FunctionRepository extends JpaRepository<SysFunction, String> {
}
